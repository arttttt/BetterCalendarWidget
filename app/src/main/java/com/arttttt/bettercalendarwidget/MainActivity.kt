package com.arttttt.bettercalendarwidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arttttt.bettercalendarwidget.ui.theme.BetterCalendarWidgetTheme
import com.arttttt.core.arch.context.wrapComponentContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val rootComponent = DaggerMainDaggerComponent
            .factory()
            .create()
            .rootComponentFactory
            .create(
                context = wrapComponentContext(
                    context = defaultComponentContext(),
                )
            )

        setContent {
            BetterCalendarWidgetTheme {
                rootComponent.content.Content(Modifier.fillMaxSize())
            }
        }
    }
}