package com.arttttt.bettercalendarwidget.components.calendar.di

import com.arttttt.bettercalendarwidget.components.calendar.CalendarComponent
import com.arttttt.bettercalendarwidget.components.calendar.CalendarComponentImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class DaggerCalendarComponentModule {

    companion object;

    @Binds
    abstract fun bindCalendarComponentFactory(factory: CalendarComponentImpl.Factory): CalendarComponent.Factory
}