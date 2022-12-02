package com.example.aptoide.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.domain.model.ParametersModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "apps_table")
class AppEntity(val appModel : List<Parameters>){
    @TypeConverters(RoomConverter::class)
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0
}