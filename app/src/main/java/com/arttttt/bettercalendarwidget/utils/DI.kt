package com.arttttt.bettercalendarwidget.utils

import android.content.Context
import com.arttttt.bettercalendarwidget.App
import com.arttttt.bettercalendarwidget.di.AppDaggerComponent

internal val Context.appDaggerComponent: AppDaggerComponent
    get() {
        return (applicationContext as App).daggerComponent
    }