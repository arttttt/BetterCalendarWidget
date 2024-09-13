package com.arttttt.bettercalendarwidget.utils

import android.content.ContentResolver
import android.provider.CalendarContract
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.arttttt.bettercalendarwidget.domain.entity.Calendar
import com.arttttt.bettercalendarwidget.domain.entity.CalendarEvent
import kotlinx.datetime.Instant
import kotlin.time.Duration

/**
 * calendar
 */
private const val CALENDAR_PROJECTION_ID_INDEX = 0
private const val CALENDAR_PROJECTION_DISPLAY_NAME_INDEX = 1
private const val CALENDAR_PROJECTION_NAME_INDEX = 2
private const val CALENDAR_PROJECTION_CALENDAR_COLOR_INDEX = 3
private const val CALENDAR_PROJECTION_VISIBLE_INDEX = 4
private const val CALENDAR_PROJECTION_SYNC_EVENTS_INDEX = 5
private const val CALENDAR_PROJECTION_ACCOUNT_NAME_INDEX = 6
private const val CALENDAR_PROJECTION_ACCOUNT_TYPE_INDEX = 7

private val CALENDAR_PROJECTION = arrayOf(
    CalendarContract.Calendars._ID,
    CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
    CalendarContract.Calendars.NAME,
    CalendarContract.Calendars.CALENDAR_COLOR,
    CalendarContract.Calendars.VISIBLE,
    CalendarContract.Calendars.SYNC_EVENTS,
    CalendarContract.Calendars.ACCOUNT_NAME,
    CalendarContract.Calendars.ACCOUNT_TYPE,
)

/**
 * events
 */
private const val PROJECTION_ID_INDEX = 0
private const val PROJECTION_TITLE_INDEX = 1
private const val PROJECTION_EVENT_LOCATION_INDEX = 2
private const val PROJECTION_STATUS_INDEX = 3
private const val PROJECTION_DTSTART_INDEX = 4
private const val PROJECTION_DTEND_INDEX = 5
private const val PROJECTION_DURATION_INDEX = 6
private const val PROJECTION_ALL_DAY_INDEX = 7
private const val PROJECTION_AVAILABILITY_INDEX = 8
private const val PROJECTION_RRULE_INDEX = 9
private const val PROJECTION_DISPLAY_COLOR_INDEX = 10
private const val PROJECTION_VISIBLE_INDEX = 11

private val EVENT_PROJECTION = arrayOf(
    CalendarContract.Events._ID,
    CalendarContract.Events.TITLE,
    CalendarContract.Events.EVENT_LOCATION,
    CalendarContract.Events.STATUS,
    CalendarContract.Events.DTSTART,
    CalendarContract.Events.DTEND,
    CalendarContract.Events.DURATION,
    CalendarContract.Events.ALL_DAY,
    CalendarContract.Events.AVAILABILITY,
    CalendarContract.Events.RRULE,
    CalendarContract.Events.DISPLAY_COLOR,
    CalendarContract.Events.VISIBLE,
)

/**
 * todo: use all fields from [CalendarContract.Calendars]
 */
fun ContentResolver.getCalendars(): List<Calendar> {
    return buildList {
        val uri = CalendarContract.Calendars.CONTENT_URI
        val selection = ""
        val selectionArgs = emptyArray<String>()
        val cur = query(
            uri,
            CALENDAR_PROJECTION,
            selection,
            selectionArgs,
            null,
        )
        while (cur?.moveToNext() == true) {
            val id = cur.getLong(CALENDAR_PROJECTION_ID_INDEX)
            val displayName = cur.getStringOrNull(CALENDAR_PROJECTION_DISPLAY_NAME_INDEX) ?: continue
            val name = cur.getStringOrNull(CALENDAR_PROJECTION_NAME_INDEX)
            val color = cur.getIntOrNull(CALENDAR_PROJECTION_CALENDAR_COLOR_INDEX) ?: continue
            val visible = cur.getIntOrNull(CALENDAR_PROJECTION_VISIBLE_INDEX)
            val syncEvents = cur.getIntOrNull(CALENDAR_PROJECTION_SYNC_EVENTS_INDEX)
            val accountName = cur.getStringOrNull(CALENDAR_PROJECTION_ACCOUNT_NAME_INDEX)
            val accountType = cur.getStringOrNull(CALENDAR_PROJECTION_ACCOUNT_TYPE_INDEX)

            this += Calendar(
                id = id,
                name = name,
                accountName = accountName,
                displayName = displayName,
                color = color,
            )
        }
        cur?.close()
    }
}

fun ContentResolver.getCalendarEvents(
    calendarId: Long,
    from: Instant,
    duration: Duration,
): List<CalendarEvent> {
    return buildList {
        val endTime = from.toEpochMilliseconds() + duration.inWholeMilliseconds

        val uri = CalendarContract.Events.CONTENT_URI
        val selection = "(${CalendarContract.Events.CALENDAR_ID} = ?) AND " +
                "(${CalendarContract.Events.DTSTART} >= ? AND ${CalendarContract.Events.DTSTART} <= ?)"
        val selectionArgs = arrayOf(
            calendarId.toString(),
            from.toEpochMilliseconds().toString(),
            endTime.toString()
        )
        val sortOrder = "${CalendarContract.Events.DTSTART} ASC"

        val cur = query(
            uri,
            EVENT_PROJECTION,
            selection,
            selectionArgs,
            sortOrder
        )
        while (cur?.moveToNext() == true) {
            val eventId = cur.getLong(PROJECTION_ID_INDEX)
            val title = cur.getStringOrNull(PROJECTION_TITLE_INDEX)
            val eventLocation = cur.getStringOrNull(PROJECTION_EVENT_LOCATION_INDEX)
            val status = cur.getIntOrNull(PROJECTION_STATUS_INDEX)
            val dtStart = cur.getLongOrNull(PROJECTION_DTSTART_INDEX)
            val dtEnd = cur.getLongOrNull(PROJECTION_DTEND_INDEX)
            val eventDuration = cur.getStringOrNull(PROJECTION_DURATION_INDEX)
            val allDay = cur.getIntOrNull(PROJECTION_ALL_DAY_INDEX) == 1
            val availability = cur.getIntOrNull(PROJECTION_AVAILABILITY_INDEX)
            val rRule = cur.getStringOrNull(PROJECTION_RRULE_INDEX)
            val displayColor = cur.getIntOrNull(PROJECTION_DISPLAY_COLOR_INDEX)
            val visible = cur.getIntOrNull(PROJECTION_VISIBLE_INDEX) == 1

            this += CalendarEvent(
                id = eventId,
                title = title ?: "No title",
                start = dtStart?.let(Instant::fromEpochMilliseconds),
                end = dtEnd?.let(Instant::fromEpochMilliseconds),
            )
        }
        cur?.close()
    }
}