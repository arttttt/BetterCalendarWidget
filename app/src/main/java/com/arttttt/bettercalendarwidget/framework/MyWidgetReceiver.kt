package com.arttttt.bettercalendarwidget.framework

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.arttttt.bettercalendarwidget.ui.widget.SmallCalendarWidget

class SmallCalendarWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = SmallCalendarWidget()
}