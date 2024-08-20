package com.arttttt.bettercalendarwidget.di

import android.content.Context
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arttttt.bettercalendarwidget.components.root.di.DaggerRootComponentModule
import com.arttttt.bettercalendarwidget.data.CalendarsRepositoryImpl
import com.arttttt.bettercalendarwidget.domain.store.CalendarStoreFactory
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

        @Singleton
        @Provides
        fun provideStoreFactory(): StoreFactory {
            return LoggingStoreFactory(
                delegate = DefaultStoreFactory(),
            )
        }

        @Provides
        @Singleton
        fun provideCalendarsStore(
            context: Context,
            storeFactory: StoreFactory,
        ): CalendarsStore {
            return CalendarStoreFactory(
                storeFactory = storeFactory,
                calendarsRepository = CalendarsRepositoryImpl(
                    context = context,
                )
            ).create()
        }
    }
}