package com.arttttt.bettercalendarwidget

import com.arttttt.bettercalendarwidget.components.root.RootComponent
import com.arttttt.bettercalendarwidget.components.root.di.DaggerRootComponentModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DaggerRootComponentModule::class,
    ]
)
internal interface MainDaggerComponent {

    @Component.Factory
    interface Factory {

        fun create(): MainDaggerComponent
    }

    val rootComponentFactory: RootComponent.Factory
}