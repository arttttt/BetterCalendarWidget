package com.arttttt.bettercalendarwidget.domain.repository

import com.arttttt.bettercalendarwidget.domain.entity.Calendar

interface CalendarRepository {

    suspend fun getCalendars(): List<Calendar>
}