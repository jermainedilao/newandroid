package com.jermaine.newandroid.uicontroller.activity.viewitem

import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.di.component.AppComponent
import javax.inject.Inject


class ViewItemViewModel : ViewModel() {

    @Inject
    lateinit var gson: Gson

    fun init(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun getRepoFromJson(repoJson: String): Repo = gson.fromJson(repoJson, Repo::class.java)
}