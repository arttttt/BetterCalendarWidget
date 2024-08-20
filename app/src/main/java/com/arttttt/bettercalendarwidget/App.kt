package com.arttttt.bettercalendarwidget

import android.app.Application
import com.arttttt.bettercalendarwidget.di.AppDaggerComponent
import com.arttttt.bettercalendarwidget.di.DaggerAppDaggerComponent

internal class App : Application() {

    val daggerComponent: AppDaggerComponent by lazy {
        DaggerAppDaggerComponent
            .factory()
            .create(
                context = this
            )
    }
}