package com.arttttt.bettercalendarwidget.components.calendar

import com.arttttt.bettercalendarwidget.ui.base.ListItem
import com.arttttt.core.arch.DecomposeComponent
import com.arttttt.core.arch.content.ComponentContentOwner
import com.arttttt.core.arch.context.AppComponentContext
import kotlinx.coroutines.flow.StateFlow

internal interface CalendarComponent : DecomposeComponent, ComponentContentOwner {

    interface Factory {

        fun create(
            calendarId: Long,
            context: AppComponentContext,
        ): CalendarComponent
    }

    data class UIState(
        val title: String,
        val items: List<ListItem>,
    )

    val uiStates: StateFlow<UIState>
}