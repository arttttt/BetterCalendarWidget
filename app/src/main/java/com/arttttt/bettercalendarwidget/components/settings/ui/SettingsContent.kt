package com.arttttt.bettercalendarwidget.components.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arttttt.bettercalendarwidget.components.settings.SettingsComponent
import com.arttttt.bettercalendarwidget.ui.base.items
import com.arttttt.core.arch.content.ComponentContent

internal class SettingsContent(
    private val component: SettingsComponent,
) : ComponentContent {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val uiState by component.uiStates.collectAsState()

        Scaffold(
            modifier = modifier
                .navigationBarsPadding()
                .statusBarsPadding(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Settings")
                    }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.items) { item ->
                    when (item) {
                        is CalendarListItem -> CalendarItemContent(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .fillParentMaxWidth()
                                .padding(8.dp),
                            item = item,
                            onHideCalendar = component::onHideCalendar,
                        )
                    }
                }
            }
        }
    }
}