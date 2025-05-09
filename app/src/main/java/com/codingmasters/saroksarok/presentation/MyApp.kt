package com.codingmasters.saroksarok.presentation

import android.app.Application
import timber.log.Timber

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        // Timber 초기화
        Timber.plant(Timber.DebugTree())
    }
}