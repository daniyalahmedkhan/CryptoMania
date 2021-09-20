package com.daniyal.cryptomania.app

import android.app.Application
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application(){


    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
    }
}