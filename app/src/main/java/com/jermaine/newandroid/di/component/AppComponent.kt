package com.jermaine.newandroid.di.component

import com.jermaine.newandroid.di.module.AppModule
import com.jermaine.newandroid.repository.ReposRepositoryImpl
import com.jermaine.newandroid.uicontroller.activity.additem.AddRepoViewModel
import com.jermaine.newandroid.uicontroller.activity.repolist.RepoListActivity
import com.jermaine.newandroid.uicontroller.activity.repolist.RepoListViewModel
import com.jermaine.newandroid.uicontroller.activity.viewitem.ViewItemViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(retrofitRepositoryServer: ReposRepositoryImpl)

    fun inject(repoListActivity: RepoListActivity)

    fun inject(repoListViewModel: RepoListViewModel)

    fun inject(viewItemViewModel: ViewItemViewModel)

    fun inject(addRepoViewModel: AddRepoViewModel)
}