package com.arttttt.bettercalendarwidget.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arttttt.core.arch.DecomposeComponent
import com.arttttt.core.arch.content.ComponentContentOwner
import com.arttttt.core.arch.context.AppComponentContext
import dagger.assisted.AssistedFactory

internal interface RootComponent : DecomposeComponent, ComponentContentOwner {

    interface Factory {

        fun create(
            context: AppComponentContext,
        ): RootComponent
    }

    val stack: Value<ChildStack<*, DecomposeComponent>>
}