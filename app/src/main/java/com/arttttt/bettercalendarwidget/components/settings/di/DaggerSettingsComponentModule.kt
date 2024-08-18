package com.arttttt.bettercalendarwidget.components.settings.di

import com.arttttt.bettercalendarwidget.components.settings.SettingsComponent
import com.arttttt.bettercalendarwidget.components.settings.SettingsComponentImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class DaggerSettingsComponentModule {

    @Binds
    abstract fun bindSettingsComponentFactory(factory: SettingsComponentImpl.Factory): SettingsComponent.Factory
}