package com.jermaine.newandroid.datamodel.repo

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface RepoDao {
    @Insert(onConflict = REPLACE)
    fun save(repo: Repo)

    @Insert(onConflict = REPLACE)
    fun save(repo: List<Repo>)

    @Query("SELECT * FROM repo")
    fun getRepos(): LiveData<List<Repo>>
}