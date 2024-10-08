package com.arttttt.core.arch

import com.arkivanov.decompose.Cancellation
import com.arkivanov.decompose.value.Value
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FlowValue<T : Any>(
    private val flow: StateFlow<T>,
) : Value<T>() {

    private var job: Job? = null

    private val lock = reentrantLock()

    private val observers = mutableMapOf<(T) -> Unit, Cancellation>()

    override val value: T
        get() = flow.value

    override fun subscribe(observer: (T) -> Unit): Cancellation {
        return try {
            lock.lock()

            if (observer in observers) return observers[observer]!!

            val cancellation = Cancellation {
                observers.remove(observer)

                if (observers.isEmpty() && job != null) {
                    unsubscribe()
                }
            }

            observers += observer to cancellation

            if (observers.size == 1 && job == null) {
                subscribe()
            }

            cancellation
        } finally {
            if (lock.isHeldByCurrentThread) {
                lock.unlock()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun subscribe() {
        job = flow
            .onEach { value ->
                try {
                    lock.lock()

                    observers.forEach { (observer, _) ->
                        observer.invoke(value)
                    }
                } finally {
                    if (lock.isHeldByCurrentThread) {
                        lock.unlock()
                    }
                }
            }
            .launchIn(GlobalScope)
    }

    private fun unsubscribe() {
        job?.cancel()
        job = null
    }
}