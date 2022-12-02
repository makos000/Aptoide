package com.example.aptoide.data.local.room

import androidx.room.TypeConverter
import com.example.aptoide.domain.model.Parameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {
    private var gson = Gson()

    @TypeConverter
    fun appsToString(appsModel: List<Parameters>): String = gson.toJson(appsModel)

    @TypeConverter
    fun stringToApps(data: String): List<Parameters> {
        val listType = object : TypeToken<List<Parameters>>() {}.type
        return gson.fromJson(data, listType)
    }
}