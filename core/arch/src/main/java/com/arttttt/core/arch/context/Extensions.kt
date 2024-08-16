package com.arttttt.core.arch.context

import com.arkivanov.decompose.GenericComponentContext

fun wrapComponentContext(
    context: GenericComponentContext<*>,
): AppComponentContext {
    return DefaultAppComponentContext(
        context = context,
    )
}