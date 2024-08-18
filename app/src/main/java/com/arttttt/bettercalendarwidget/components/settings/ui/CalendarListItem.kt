package com.arttttt.bettercalendarwidget.components.settings.ui

import androidx.compose.ui.graphics.Color
import com.arttttt.bettercalendarwidget.ui.base.ListItem

internal data class CalendarListItem(
    val id: Long,
    val title: String,
    val subtitle: String,
    val color: Color,
    val isChecked: Boolean,
) : ListItem {

    override val key: Any by this::id
}