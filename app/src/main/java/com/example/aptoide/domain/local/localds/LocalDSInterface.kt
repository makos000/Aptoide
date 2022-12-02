package com.example.aptoide.domain.local.localds

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aptoide.data.local.room.AppEntity
import kotlinx.coroutines.flow.Flow

interface LocalDSInterface {
    fun insertAppToDB(appEntity: AppEntity)

    fun readAppsFromDB(): Flow<List<AppEntity>>

    fun nukeTable()
}