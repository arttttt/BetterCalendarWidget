package com.arttttt.bettercalendarwidget.domain.entity

import kotlinx.datetime.Instant

data class CalendarEvent(
    val id: Long,
    val title: String,
    val start: Instant?,
    val end: Instant?,
)