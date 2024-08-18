package com.arttttt.bettercalendarwidget.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arttttt.core.arch.DecomposeComponent
import com.arttttt.core.arch.context.AppComponentContext
import kotlinx.serialization.Serializable

internal class RootComponentImpl(
    context: AppComponentContext
) : RootComponent,
    AppComponentContext by context {

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Settings : Config
    }

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
            is Config.Settings -> TODO()
        }
    }
}