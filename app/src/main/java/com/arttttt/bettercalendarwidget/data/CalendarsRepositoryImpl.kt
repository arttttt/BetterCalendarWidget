package com.arttttt.bettercalendarwidget.data

import android.content.Context
import com.arttttt.bettercalendarwidget.domain.entity.Calendar
import com.arttttt.bettercalendarwidget.domain.repository.CalendarsRepository
import com.arttttt.bettercalendarwidget.utils.getCalendars

class CalendarsRepositoryImpl(
    private val context: Context,
) : CalendarsRepository {

    override suspend fun getCalendars(): Result<List<Calendar>> {
        return runCatching {
            context
                .contentResolver
                .getCalendars()
        }
    }
}