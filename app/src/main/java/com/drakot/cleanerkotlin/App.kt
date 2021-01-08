package com.drakot.cleanerkotlin

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        App.Companion.context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}
