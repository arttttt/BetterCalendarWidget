package com.arttttt.bettercalendarwidget.di

import android.content.Context
import com.arttttt.bettercalendarwidget.components.root.RootComponent
import com.arttttt.bettercalendarwidget.domain.store.CalendarsStore
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppDaggerModule::class
    ],
)
internal interface AppDaggerComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
        ): AppDaggerComponent
    }

    val rootComponentFactory: RootComponent.Factory

    /**
     * todo: remove later
     */
    val calendarsStore: CalendarsStore
}