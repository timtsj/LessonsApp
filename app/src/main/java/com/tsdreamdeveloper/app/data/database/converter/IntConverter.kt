package com.tsdreamdeveloper.app.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.util.*
import java.util.Collections.emptyList

class IntConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromInts(value: List<Int>): String = gson.toJson(value)

    @TypeConverter
    fun fromString(value: String): List<Int> {
        val listType = object : TypeToken<ArrayList<Int>>() {}.type
        return try {
            gson.fromJson(value, listType)
        } catch (e: JsonSyntaxException) {
            emptyList()
        }
    }

}