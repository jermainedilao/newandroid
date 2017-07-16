package com.jermaine.newandroid.datamodel.repo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Repo(@PrimaryKey val id: String?, val name: String?, val description: String?)