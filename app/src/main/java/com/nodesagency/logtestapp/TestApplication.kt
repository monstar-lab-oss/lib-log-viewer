package com.nodesagency.logtestapp

import android.app.Application
import com.nodesagency.logviewer.Logger
import com.nodesagency.logviewer.LoggerTree
import timber.log.Timber

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize the logger library here
        Logger.initialize(this)
        Timber.plant(LoggerTree())
    }
}
