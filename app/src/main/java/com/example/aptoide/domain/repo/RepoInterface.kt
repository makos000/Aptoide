package com.example.aptoide.domain.repo

import com.example.aptoide.data.local.room.AppEntity
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    suspend fun getApps(): Flow<Resource<List<AppEntity>>>

    fun insertAppsToDB(appEntity: AppEntity)

    fun readAppsfromDB(): Flow<List<AppEntity>>

    fun nukeTable()

    suspend fun getAppsFromApi(): Resource<List<Parameters>>
}