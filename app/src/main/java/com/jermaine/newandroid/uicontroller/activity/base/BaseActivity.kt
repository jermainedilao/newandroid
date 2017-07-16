package com.jermaine.newandroid.uicontroller.activity.base


import android.arch.lifecycle.LifecycleActivity
import com.jermaine.newandroid.app.App
import com.jermaine.newandroid.di.component.AppComponent

open class BaseActivity : LifecycleActivity() {
    val component: AppComponent
        get() = (application as App).component
}
