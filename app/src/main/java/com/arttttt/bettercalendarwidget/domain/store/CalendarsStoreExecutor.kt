package com.arttttt.bettercalendarwidget.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arttttt.bettercalendarwidget.domain.repository.CalendarsRepository
import kotlinx.coroutines.launch

class CalendarsStoreExecutor(
    private val calendarsRepository: CalendarsRepository,
) : CoroutineExecutor<CalendarsStore.Intent, CalendarsStore.Action, CalendarsStore.State, CalendarsStore.Message, CalendarsStore.Label>() {

    override fun executeAction(action: CalendarsStore.Action) {
        super.executeAction(action)
    }

    override fun executeIntent(intent: CalendarsStore.Intent) {
        when (intent) {
            is CalendarsStore.Intent.LoadCalendars -> loadCalendars()
            is CalendarsStore.Intent.HideCalendar -> hideCalendar(intent.id)
        }
    }

    private fun loadCalendars() {
        if (state().isInProgress) return

        scope
            .launch {
                dispatch(CalendarsStore.Message.ProgressStared)

                calendarsRepository
                    .getCalendars()
                    .onSuccess { calendars ->
                        dispatch(CalendarsStore.Message.CalendarsLoaded(calendars))
                    }
            }
            .invokeOnCompletion {
                dispatch(CalendarsStore.Message.ProgressFinished)
            }
    }

    /**
     * it filters in-memory cache only
     *
     * todo: make it persistent
     */
    private fun hideCalendar(id: Long) {
        dispatch(
            CalendarsStore.Message.CalendarsLoaded(
                state().calendars.filter { it.id != id }
            )
        )
    }
}