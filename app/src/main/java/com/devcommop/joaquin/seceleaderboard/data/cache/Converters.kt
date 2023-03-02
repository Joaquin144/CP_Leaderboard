package com.devcommop.joaquin.seceleaderboard.data.cache

import androidx.room.TypeConverter
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Contest
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Result
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Row
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromValueToString(value: PartiesScore?): String? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<ArrayList<PartiesScore>>(){}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToValue(value: String?): PartiesScore? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<ArrayList<PartiesScore>>(){}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromValueToString2(value: com.devcommop.joaquin.seceleaderboard.data.remote.dto.Result?): String? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<ArrayList<com.devcommop.joaquin.seceleaderboard.data.remote.dto.Result>>(){}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToValue2(value: String?): com.devcommop.joaquin.seceleaderboard.data.remote.dto.Result? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<ArrayList<com.devcommop.joaquin.seceleaderboard.data.remote.dto.Result>>(){}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromValueToString3(value: Contest?): String? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<ArrayList<Contest>>(){}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToValue3(value: String?): Contest? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<ArrayList<Contest>>(){}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromValueToString4(value: List<Row>?): String? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<List<Row>>(){}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToValue4(value: String?): List<Row>? {
        if (value == null)
            return null
        val gson = GsonBuilder().setLenient().create()
        val type = object: TypeToken<List<Row>>(){}.type
        return gson.fromJson(value, type)
    }
}