package com.jermaine.newandroid.app


import android.app.Application

import com.jermaine.newandroid.di.component.AppComponent
import com.jermaine.newandroid.di.component.DaggerAppComponent
import com.jermaine.newandroid.di.module.AppModule

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}