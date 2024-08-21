package com.arttttt.bettercalendarwidget.components.settings

import androidx.compose.ui.graphics.Color
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arttttt.bettercalendarwidget.components.settings.ui.CalendarListItem
import com.arttttt.bettercalendarwidget.components.settings.ui.SettingsContent
import com.arttttt.bettercalendarwidget.domain.store.CalendarsStore
import com.arttttt.core.arch.content.ComponentContent
import com.arttttt.core.arch.context.AppComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class SettingsComponentImpl @AssistedInject constructor(
    @Assisted context: AppComponentContext,
    private val calendarsStore: CalendarsStore,
) : SettingsComponent,
    AppComponentContext by context {

    @AssistedFactory
    interface Factory : SettingsComponent.Factory {

        override fun create(context: AppComponentContext): SettingsComponentImpl
    }

    private val coroutineScope = coroutineScope()

    override val content: ComponentContent = SettingsContent(this)

    override val uiStates = calendarsStore
        .states
        .map { state -> state.toUIState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = calendarsStore.state.toUIState(),
        )

    override fun onHideCalendar(id: Long) {
        calendarsStore.accept(CalendarsStore.Intent.HideCalendar(id))
    }

    private fun CalendarsStore.State.toUIState(): SettingsComponent.UIState {
        return SettingsComponent.UIState(
            items = calendars.map { calendar ->
                CalendarListItem(
                    id = calendar.id,
                    title = calendar.displayName,
                    subtitle = calendar.accountName ?: "No account name",
                    color = Color(calendar.color),
                    isChecked = false,
                )
            },
        )
    }
}