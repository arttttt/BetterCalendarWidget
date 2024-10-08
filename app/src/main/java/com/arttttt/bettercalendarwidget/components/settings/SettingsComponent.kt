package com.arttttt.bettercalendarwidget.components.settings

import com.arttttt.bettercalendarwidget.ui.base.ListItem
import com.arttttt.core.arch.DecomposeComponent
import com.arttttt.core.arch.content.ComponentContentOwner
import com.arttttt.core.arch.context.AppComponentContext
import kotlinx.coroutines.flow.StateFlow

internal interface SettingsComponent : DecomposeComponent, ComponentContentOwner {

    interface Factory {

        fun create(
            context: AppComponentContext,
            onCalendarClicked: (Long) -> Unit,
        ): SettingsComponent
    }

    data class UIState(
        val items: List<ListItem>,
    )

    val uiStates: StateFlow<UIState>

    val onCalendarClicked: (Long) -> Unit

    fun onHideCalendar(id: Long)
}