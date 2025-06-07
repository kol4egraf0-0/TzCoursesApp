package com.example.courseappem.serv

import android.app.Application
import com.example.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CourseAppEm : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CourseAppEm)
            modules(appModule)
        }
    }
}