package com.example.aptoide.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [AppEntity::class],
    version = 3,
    exportSchema = false
)


@TypeConverters(RoomConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}