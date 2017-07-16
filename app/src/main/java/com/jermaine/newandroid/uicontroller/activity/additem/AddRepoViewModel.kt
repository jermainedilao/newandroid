package com.jermaine.newandroid.uicontroller.activity.additem

import android.arch.lifecycle.ViewModel
import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.di.component.AppComponent
import com.jermaine.newandroid.repository.ReposRepositoryImpl
import javax.inject.Inject


class AddRepoViewModel : ViewModel() {

    @Inject
    lateinit var repository: ReposRepositoryImpl

    fun init(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun addRepo(id: String, name: String, description: String) {
        val repo: Repo = Repo(id, name, description)
        repository.save(repo)
    }
}