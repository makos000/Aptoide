package com.example.aptoide.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.aptoide.domain.model.Parameters

@Entity(tableName = "apps_table")
class AppEntity(val appModel : List<Parameters>){
    @TypeConverters(RoomConverter::class)
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0
}