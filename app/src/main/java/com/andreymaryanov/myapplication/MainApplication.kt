package com.andreymaryanov.myapplication

import android.app.Application
import com.andreymaryanov.myapplication.infrastructure.Bootstrapper
import com.andreymaryanov.myapplication.models.ApplicationContext
import com.andreymaryanov.myapplication.models.PlatformValues

class MainApplication : Application() {

    lateinit var bootstrapper : Bootstrapper
        private set

    override fun onCreate() {
        super.onCreate()
        setupApplication()
    }

    private fun setupApplication() {
        bootstrapper = Bootstrapper(
                createPlatformValues())
    }

    private fun createPlatformValues() = PlatformValues(ApplicationContext(this.applicationContext))
}