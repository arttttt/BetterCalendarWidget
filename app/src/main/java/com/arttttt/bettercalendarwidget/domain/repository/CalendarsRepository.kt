package com.arttttt.bettercalendarwidget.domain.repository

import com.arttttt.bettercalendarwidget.domain.entity.Calendar

interface CalendarsRepository {

    suspend fun getCalendars(): Result<List<Calendar>>
}