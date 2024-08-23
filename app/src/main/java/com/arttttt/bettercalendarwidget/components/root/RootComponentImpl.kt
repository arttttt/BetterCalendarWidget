package com.arttttt.bettercalendarwidget.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arttttt.bettercalendarwidget.components.calendar.CalendarComponent
import com.arttttt.bettercalendarwidget.components.settings.SettingsComponent
import com.arttttt.core.arch.DecomposeComponent
import com.arttttt.core.arch.content.ComponentContent
import com.arttttt.core.arch.context.AppComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

internal class RootComponentImpl @AssistedInject constructor(
    @Assisted context: AppComponentContext,
    private val settingsComponentFactory: SettingsComponent.Factory,
    private val calendarComponentFactory: CalendarComponent.Factory,
) : RootComponent,
    AppComponentContext by context {

    @AssistedFactory
    interface Factory : RootComponent.Factory {

        override fun create(context: AppComponentContext): RootComponentImpl
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Settings : Config

        @Serializable
        data class Calendar(val calendarId: Long) : Config
    }

    override val content: ComponentContent = RootContent(this)

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, DecomposeComponent>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Settings,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        context: AppComponentContext
    ): DecomposeComponent {
        return when (config) {
            is Config.Settings -> settingsComponentFactory.create(
                context = context,
                onCalendarClicked = { calendarId -> navigation.bringToFront(Config.Calendar(calendarId)) },
            )
            is Config.Calendar -> calendarComponentFactory.create(config.calendarId, context)
        }
    }
}