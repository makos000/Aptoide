package com.example.aptoide.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAppsToDB(appEntity: AppEntity)

    @Query("SELECT * FROM apps_table order by `index` DESC")
    fun readAppsFromDB(): Flow<List<AppEntity>>

    @Query("DELETE FROM apps_table")
    fun nukeTable()
}