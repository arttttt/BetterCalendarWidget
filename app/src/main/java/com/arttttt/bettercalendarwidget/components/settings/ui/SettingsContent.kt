package com.arttttt.bettercalendarwidget.components.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.arttttt.bettercalendarwidget.components.settings.SettingsComponent
import com.arttttt.bettercalendarwidget.ui.base.items
import com.arttttt.bettercalendarwidget.utils.getCalendars
import com.arttttt.core.arch.content.ComponentContent

internal class SettingsContent(
    private val component: SettingsComponent,
) : ComponentContent {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        //val uiState by component.uiStates.collectAsState()

        val context = LocalContext.current
        val items = remember {
            context
                .contentResolver
                .getCalendars()
                .map { calendar ->
                    CalendarListItem(
                        id = calendar.id,
                        title = calendar.displayName,
                        subtitle = calendar.accountName ?: "No account name",
                        color = Color(calendar.color),
                        isChecked = false,
                    )
                }
        }

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
                items(items) { item ->
                    when (item) {
                        is CalendarListItem -> CalendarItemContent(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(8.dp),
                            item = item,
                        )
                    }
                }
            }
        }
    }
}