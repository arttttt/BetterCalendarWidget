package com.arttttt.bettercalendarwidget.components.settings

import com.arttttt.core.arch.content.ComponentContent
import com.arttttt.core.arch.context.AppComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class SettingsComponentImpl @AssistedInject constructor(
    @Assisted context: AppComponentContext,
) : SettingsComponent,
    AppComponentContext by context {

    @AssistedFactory
    interface Factory : SettingsComponent.Factory {

        override fun create(context: AppComponentContext): SettingsComponentImpl
    }

    override val content: ComponentContent = SettingsContent(this)
}