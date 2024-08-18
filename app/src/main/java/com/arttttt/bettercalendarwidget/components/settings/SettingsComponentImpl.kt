package com.arttttt.bettercalendarwidget.components.settings

import com.arttttt.bettercalendarwidget.components.settings.ui.SettingsContent
import com.arttttt.core.arch.content.ComponentContent
import com.arttttt.core.arch.context.AppComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow

internal class SettingsComponentImpl @AssistedInject constructor(
    @Assisted context: AppComponentContext,
) : SettingsComponent,
    AppComponentContext by context {

    @AssistedFactory
    interface Factory : SettingsComponent.Factory {

        override fun create(context: AppComponentContext): SettingsComponentImpl
    }

    override val content: ComponentContent = SettingsContent(this)

    override val uiStates: StateFlow<SettingsComponent.UiState>
        get() = TODO("Not yet implemented")
}