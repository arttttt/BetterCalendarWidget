package com.arttttt.bettercalendarwidget

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arttttt.bettercalendarwidget.ui.theme.BetterCalendarWidgetTheme
import com.arttttt.bettercalendarwidget.utils.getCalendars

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BetterCalendarWidgetTheme {
            }
        }

        Log.e(
            "Calendar",
            contentResolver.getCalendars().joinToString(separator = "\n") { it.toString() },
        )
    }
}