package com.jermaine.newandroid.uicontroller.activity.repolist

import android.arch.lifecycle.ViewModel
import com.jermaine.newandroid.di.component.AppComponent
import com.jermaine.newandroid.repository.ReposRepositoryImpl
import com.jermaine.newandroid.repository.callback.OnFetchReposCallback
import javax.inject.Inject


class RepoListViewModel : ViewModel() {

    @Inject
    lateinit var reposRepository: ReposRepositoryImpl

    fun init(appComponent: AppComponent) {
        appComponent.inject(this)


    }

    fun fetchRepos(callback: OnFetchReposCallback) {
        reposRepository.fetchRepos(callback)
    }
}