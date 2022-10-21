package com.szn.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenderConverter {

    @TypeConverter
    fun fromList(value: List<Genre>): String {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromString(value: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, type)
    }

}