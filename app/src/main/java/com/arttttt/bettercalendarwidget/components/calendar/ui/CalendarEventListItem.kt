package com.arttttt.bettercalendarwidget.components.calendar.ui

import com.arttttt.bettercalendarwidget.ui.base.ListItem

data class CalendarEventListItem(
    val id: Long,
    val title: String,
    val start: String,
) : ListItem {

    override val key: Any by this::id
}