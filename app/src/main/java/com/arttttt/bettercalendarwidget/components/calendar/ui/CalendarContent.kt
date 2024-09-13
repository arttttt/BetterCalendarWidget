package com.arttttt.bettercalendarwidget.components.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.arttttt.bettercalendarwidget.components.calendar.CalendarComponent
import com.arttttt.bettercalendarwidget.ui.base.items
import com.arttttt.bettercalendarwidget.utils.getCalendarEvents
import com.arttttt.core.arch.content.ComponentContent
import kotlinx.datetime.Clock
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal class CalendarContent(
    private val component: CalendarComponent,
) : ComponentContent {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val context = LocalContext.current

        val items = context
            .contentResolver
            .getCalendarEvents(
                calendarId = component.calendarId,
                from = Clock.System.now(),
                duration = 7.toDuration(DurationUnit.DAYS),
            )
            .map { event ->
                CalendarEventListItem(
                    id = event.id,
                    title = event.title,
                    start = event.start?.format(
                        DateTimeComponents.Format {
                            monthName(MonthNames.ENGLISH_ABBREVIATED)
                            char(' ')
                            dayOfMonth()
                            char(' ')
                            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
                            char(' ')
                            year()
                        }
                    ) ?: "null"
                )
            }

        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text("Calendar")
                    },
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(items) { item ->
                    when (item) {
                        is CalendarEventListItem -> CalendarEventItemContent(
                            modifier = Modifier,
                            item = item,
                        )
                    }
                }
            }
        }
    }
}