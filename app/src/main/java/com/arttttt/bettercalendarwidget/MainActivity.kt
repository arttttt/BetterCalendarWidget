package com.arttttt.bettercalendarwidget

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arttttt.bettercalendarwidget.domain.store.CalendarsStore
import com.arttttt.bettercalendarwidget.ui.theme.BetterCalendarWidgetTheme
import com.arttttt.bettercalendarwidget.utils.appDaggerComponent
import com.arttttt.core.arch.context.wrapComponentContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )

        /**
         * todo: remove later
         */
        appDaggerComponent.calendarsStore.accept(CalendarsStore.Intent.LoadCalendars)

        val rootComponent = appDaggerComponent
            .rootComponentFactory
            .create(
                context = wrapComponentContext(
                    context = defaultComponentContext(),
                )
            )

        setContent {
            BetterCalendarWidgetTheme {
                CompositionLocalProvider {
                    rootComponent.content.Content(Modifier.fillMaxSize())
                }
            }
        }
    }
}