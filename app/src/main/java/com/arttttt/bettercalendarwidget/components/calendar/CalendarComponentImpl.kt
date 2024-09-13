package com.arttttt.bettercalendarwidget.components.calendar

import com.arttttt.bettercalendarwidget.components.calendar.ui.CalendarContent
import com.arttttt.core.arch.content.ComponentContent
import com.arttttt.core.arch.context.AppComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow

internal class CalendarComponentImpl @AssistedInject constructor(
    @Assisted override val calendarId: Long,
    @Assisted context: AppComponentContext,
) : CalendarComponent,
    AppComponentContext by context {

    @AssistedFactory
    interface Factory : CalendarComponent.Factory {

        override fun create(calendarId: Long, context: AppComponentContext): CalendarComponentImpl
    }

    override val content: ComponentContent = CalendarContent(this)

    override val uiStates: StateFlow<CalendarComponent.UIState>
        get() = TODO("Not yet implemented")

}