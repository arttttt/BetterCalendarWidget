package com.arttttt.bettercalendarwidget.ui.widget

import android.content.Context
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text

class SmallCalendarWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            GlanceTheme {
                Scaffold {
                    Row(
                        modifier = GlanceModifier
                            .fillMaxSize()
                            .padding(4.dp),
                    ) {
                        Column(
                            modifier = GlanceModifier
                                .fillMaxHeight()
                                .padding(
                                    horizontal = 12.dp,
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = "Tue")
                            Text("04")
                            Text("Oct")
                        }

                        Column(
                            modifier = GlanceModifier.fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text("Calendar event title")
                            Text("Calendar event details")
                        }
                    }
                }
            }
        }
    }
}