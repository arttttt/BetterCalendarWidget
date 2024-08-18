package com.arttttt.bettercalendarwidget.components.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arttttt.bettercalendarwidget.ui.theme.BetterCalendarWidgetTheme

@Composable
internal fun CalendarItemContent(
    modifier: Modifier,
    item: CalendarListItem,
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(
            modifier = Modifier
                .width(
                    16.dp
                )
                .fillMaxHeight()
                .background(item.color)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Checkbox(
            checked = item.isChecked,
            onCheckedChange = {},
        )
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun CalendarItemContentPreview() {
    BetterCalendarWidgetTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CalendarItemContent(
                modifier = Modifier,
                item = CalendarListItem(
                    id = 0,
                    title = "Calendar title",
                    color = Color.LightGray,
                    subtitle = "Calendar subtitle",
                    isChecked = true,
                )
            )
        }
    }
}