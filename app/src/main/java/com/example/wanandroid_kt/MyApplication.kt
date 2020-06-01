package com.example.wanandroid_kt

import android.app.Application
import android.content.Context
import android.hardware.display.DisplayManager
import kotlin.properties.Delegates

class MyApplication : Application(){

    companion object{
        private val TAG = "MyApplication"

        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}