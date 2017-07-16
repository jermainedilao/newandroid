package com.jermaine.newandroid.repository.callback


import android.arch.lifecycle.LiveData

import com.jermaine.newandroid.datamodel.repo.Repo

interface OnFetchReposCallback {
    fun onSuccess(repoList: LiveData<List<Repo>>)

    fun onError()
}
