package com.arttttt.bettercalendarwidget.di

import android.content.Context
import com.arttttt.bettercalendarwidget.components.root.di.DaggerRootComponentModule
import com.arttttt.bettercalendarwidget.data.CalendarsRepositoryImpl
import com.arttttt.bettercalendarwidget.domain.store.CalendarsStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DaggerRootComponentModule::class,
    ]
)
abstract class AppDaggerModule {

    companion object {

        @Provides
        @Singleton
        fun provideCalendarsStore(
            context: Context,
        ): CalendarsStore {
            return CalendarsStore(
                calendarsRepository = CalendarsRepositoryImpl(
                    context = context,
                ),
            )
        }
    }
}