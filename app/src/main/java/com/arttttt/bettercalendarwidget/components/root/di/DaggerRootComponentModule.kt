package com.arttttt.bettercalendarwidget.components.root.di

import com.arttttt.bettercalendarwidget.components.root.RootComponent
import com.arttttt.bettercalendarwidget.components.root.RootComponentImpl
import com.arttttt.bettercalendarwidget.components.settings.di.DaggerSettingsComponentModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        DaggerSettingsComponentModule::class,
    ]
)
internal abstract class DaggerRootComponentModule {

    @Binds
    abstract fun bindRootComponentFactory(factory: RootComponentImpl.Factory): RootComponent.Factory
}