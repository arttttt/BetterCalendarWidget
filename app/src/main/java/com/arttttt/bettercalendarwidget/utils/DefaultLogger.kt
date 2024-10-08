package com.arttttt.bettercalendarwidget.utils

import android.util.Log
import com.arttttt.simplemvi.logging.logger.Logger

object DefaultLogger : Logger {

    override fun log(message: String) {
        Log.i("SimpleMVI", message)
    }
}