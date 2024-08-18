package com.arttttt.bettercalendarwidget.components.settings

import com.arttttt.core.arch.DecomposeComponent
import com.arttttt.core.arch.content.ComponentContentOwner
import com.arttttt.core.arch.context.AppComponentContext

internal interface SettingsComponent : DecomposeComponent, ComponentContentOwner {

    interface Factory {

        fun create(
            context: AppComponentContext,
        ): SettingsComponent
    }
}