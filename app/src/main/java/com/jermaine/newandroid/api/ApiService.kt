package com.jermaine.newandroid.api


import com.jermaine.newandroid.datamodel.repo.Repo

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @get:GET("/users/jermainedilao/repos")
    val repos: Call<List<Repo>>
}