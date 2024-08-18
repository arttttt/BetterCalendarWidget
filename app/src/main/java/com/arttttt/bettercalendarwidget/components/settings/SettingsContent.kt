package com.arttttt.bettercalendarwidget.components.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arttttt.core.arch.content.ComponentContent

internal class SettingsContent(
    private val component: SettingsComponent,
) : ComponentContent {

    @Composable
    override fun Content(modifier: Modifier) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {

            Text("settings")
        }
    }
}