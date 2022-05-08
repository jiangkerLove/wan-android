package com.study.xml_view

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ctx = this
    }

    companion object {
        lateinit var ctx: Application
    }

}