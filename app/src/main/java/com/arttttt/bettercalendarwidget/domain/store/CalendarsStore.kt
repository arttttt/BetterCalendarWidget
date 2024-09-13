package com.arttttt.bettercalendarwidget.domain.store

import com.arttttt.bettercalendarwidget.domain.entity.Calendar
import com.arttttt.bettercalendarwidget.domain.repository.CalendarsRepository
import com.arttttt.bettercalendarwidget.utils.DefaultLogger
import com.arttttt.simplemvi.store.Store
import com.arttttt.simplemvi.utils.actorDsl
import com.arttttt.simplemvi.utils.createStore
import com.arttttt.simplemvi.utils.loggingActor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalendarsStore(
    calendarsRepository: CalendarsRepository,
) : Store<CalendarsStore.Intent, CalendarsStore.State, CalendarsStore.SideEffect> by createStore(
    initialState = State(
        isInProgress = false,
        calendars = emptyList(),
    ),
    initialIntents = listOf(Intent.LoadCalendars),
    actor = loggingActor(
        name = "CalendarsStore",
        logger = DefaultLogger,
        delegate = actorDsl(
            coroutineContext = Dispatchers.Main.immediate,
        ) {
            onIntent<Intent.LoadCalendars> {
                if (getState().isInProgress) return@onIntent

                launch {
                    reduce { state ->
                        state.copy(
                            isInProgress = true,
                        )
                    }

                    calendarsRepository
                        .getCalendars()
                        .onSuccess { calendars ->
                            reduce { state ->
                                state.copy(
                                    calendars = calendars,
                                )
                            }
                        }
                }
                    .invokeOnCompletion {
                        reduce { state ->
                            state.copy(
                                isInProgress = false,
                            )
                        }
                    }
            }
            onIntent<Intent.HideCalendar> { intent ->
                reduce { state ->
                    state.copy(
                        calendars = state.calendars.filter { it.id != intent.id }
                    )
                }
            }
        },
    )
) {

    sealed interface Intent {

        data object LoadCalendars : Intent
        data class HideCalendar(val id: Long) : Intent
    }

    data class State(
        val isInProgress: Boolean,
        val calendars: List<Calendar>,
    )

    sealed interface SideEffect
}