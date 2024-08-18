package com.arttttt.bettercalendarwidget.components.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arttttt.core.arch.content.ComponentContent
import com.arttttt.core.arch.content.ComponentContentOwner

internal class RootContent(
    private val component: RootComponent,
) : ComponentContent {

    @Composable
    override fun Content(modifier: Modifier) {
        Children(
            stack = component.stack
        ) { child ->
            when (val instance = child.instance) {
                is ComponentContentOwner -> instance.content.Content(modifier)
            }
        }
    }
}