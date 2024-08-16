package com.arttttt.core.arch.context

import com.arkivanov.decompose.ComponentContextFactory
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.GenericComponentContext
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.statekeeper.StateKeeperOwner

class DefaultAppComponentContext(
    context: GenericComponentContext<*>,
) : AppComponentContext,
    LifecycleOwner by context,
    StateKeeperOwner by context,
    InstanceKeeperOwner by context,
    BackHandlerOwner by context {

    override val componentContextFactory =
        ComponentContextFactory<AppComponentContext> { lifecycle, stateKeeper, instanceKeeper, backHandler ->
            DefaultAppComponentContext(
                context = DefaultComponentContext(
                    lifecycle = lifecycle,
                    stateKeeper = stateKeeper,
                    instanceKeeper = instanceKeeper,
                    backHandler = backHandler,
                ),
            )
        }
}