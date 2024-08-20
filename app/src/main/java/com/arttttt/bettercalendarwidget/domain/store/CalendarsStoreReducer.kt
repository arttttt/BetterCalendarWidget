package com.arttttt.bettercalendarwidget.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer

object CalendarsStoreReducer : Reducer<CalendarsStore.State, CalendarsStore.Message> {

    override fun CalendarsStore.State.reduce(msg: CalendarsStore.Message): CalendarsStore.State {
        return when (msg) {
            is CalendarsStore.Message.ProgressStared -> copy(
                isInProgress = true,
            )
            is CalendarsStore.Message.ProgressFinished -> copy(
                isInProgress = false,
            )
            is CalendarsStore.Message.CalendarsLoaded -> copy(
                calendars = msg.calendars,
            )
        }
    }
}