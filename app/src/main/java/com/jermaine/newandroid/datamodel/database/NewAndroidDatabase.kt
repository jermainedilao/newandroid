package com.jermaine.newandroid.datamodel.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jermaine.newandroid.datamodel.repo.Repo
import com.jermaine.newandroid.datamodel.repo.RepoDao


@Database(entities = arrayOf(Repo::class), version = 1)
abstract class NewAndroidDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}