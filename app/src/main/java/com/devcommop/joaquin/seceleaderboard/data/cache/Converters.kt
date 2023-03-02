package com.devcommop.joaquin.seceleaderboard.data.cache

import androidx.room.TypeConverter
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromValueToString(value: Result?): String? {
        if (value == null)
            return null
        val gson = Gson()
        val type = object: TypeToken<ArrayList<Result>>(){}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToValue(value: String?): Result? {
        if (value == null)
            return null
        val gson = Gson()
        val type = object: TypeToken<ArrayList<Result>>(){}.type
        return gson.fromJson(value, type)
    }
}