package com.arttttt.core.arch

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private class ValueStateFlow<out T : Any>(
    private val v: Value<T>,
) : StateFlow<T> {
    override val value: T get() = v.value
    override val replayCache: List<T> get() = listOf(v.value)

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        val flow = MutableStateFlow(v.value)
        val observer: (T) -> Unit = { flow.value = it }
        val cancellation = v.subscribe(observer)

        try {
            flow.collect(collector)
        } finally {
            cancellation.cancel()
        }
    }
}

fun <T : Any> Value<T>.asStateFlow(): StateFlow<T> = ValueStateFlow(this)