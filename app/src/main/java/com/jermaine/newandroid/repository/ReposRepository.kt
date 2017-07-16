package com.jermaine.newandroid.repository


import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.repository.callback.OnFetchReposCallback

interface ReposRepository {
    fun save(repo: Repo)

    fun save(repoList: List<Repo>)

    fun fetchRepos(callback: OnFetchReposCallback)
}
