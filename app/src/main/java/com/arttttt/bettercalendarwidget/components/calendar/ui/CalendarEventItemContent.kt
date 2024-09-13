package com.arttttt.bettercalendarwidget.components.calendar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CalendarEventItemContent(
    modifier: Modifier,
    item: CalendarEventListItem
) {

    Column(
        modifier = modifier,
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(item.start)
    }
}