package com.arttttt.bettercalendarwidget.utils

import android.content.ContentResolver
import android.provider.CalendarContract
import com.arttttt.bettercalendarwidget.domain.entity.Calendar

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
            selection, selectionArgs,
            null,
        )
        while (cur?.moveToNext() == true) {
            val id = cur.getLong(CALENDAR_PROJECTION_ID_INDEX)
            val displayName = cur.getString(CALENDAR_PROJECTION_DISPLAY_NAME_INDEX)
            val name = cur.getString(CALENDAR_PROJECTION_NAME_INDEX)
            val color = cur.getInt(CALENDAR_PROJECTION_CALENDAR_COLOR_INDEX)
            val visible = cur.getInt(CALENDAR_PROJECTION_VISIBLE_INDEX)
            val syncEvents = cur.getInt(CALENDAR_PROJECTION_SYNC_EVENTS_INDEX)
            val accountName = cur.getString(CALENDAR_PROJECTION_ACCOUNT_NAME_INDEX)
            val accountType = cur.getString(CALENDAR_PROJECTION_ACCOUNT_TYPE_INDEX)

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