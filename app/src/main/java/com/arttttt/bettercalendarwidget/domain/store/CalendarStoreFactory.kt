package com.arttttt.bettercalendarwidget.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arttttt.bettercalendarwidget.domain.repository.CalendarsRepository

class CalendarStoreFactory(
    private val storeFactory: StoreFactory,
    private val calendarsRepository: CalendarsRepository,
) {

    fun create(): CalendarsStore {
        val initialState = CalendarsStore.State(
            isInProgress = false,
            calendars = emptyList(),
        )
        val name = CalendarsStore::class.qualifiedName

        return object : CalendarsStore, Store<CalendarsStore.Intent, CalendarsStore.State, CalendarsStore.Label> by storeFactory.create(
            name = name,
            initialState = initialState,
            bootstrapper = null,
            executorFactory = {
                CalendarsStoreExecutor(
                    calendarsRepository = calendarsRepository,
                )
            },
            reducer = CalendarsStoreReducer,
        ) {}
    }
}