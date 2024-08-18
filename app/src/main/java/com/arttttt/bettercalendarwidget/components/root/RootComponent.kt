package com.arttttt.bettercalendarwidget.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arttttt.core.arch.DecomposeComponent

internal interface RootComponent : DecomposeComponent {

    val stack: Value<ChildStack<*, DecomposeComponent>>
}