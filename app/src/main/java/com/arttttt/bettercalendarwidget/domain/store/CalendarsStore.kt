package com.arttttt.bettercalendarwidget.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arttttt.bettercalendarwidget.domain.entity.Calendar

interface CalendarsStore : Store<CalendarsStore.Intent, CalendarsStore.State, CalendarsStore.Label> {

    data class State(
        val isInProgress: Boolean,
        val calendars: List<Calendar>,
    )

    sealed interface Action

    sealed interface Intent {

        data object LoadCalendars : Intent
        data class HideCalendar(val id: Long) : Intent
    }

    sealed interface Message {

        data object ProgressStared : Message
        data object ProgressFinished : Message

        data class CalendarsLoaded(val calendars: List<Calendar>) : Message
    }

    sealed interface Label
}