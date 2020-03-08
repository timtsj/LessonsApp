package com.tsdreamdeveloper.app.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class HashMapConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromHashMap(value: HashMap<String, String>): String = gson.toJson(value)

    @TypeConverter
    fun fromString(value: String): HashMap<String, String> {
        val listType = object : TypeToken<HashMap<String, String>>() {}.type
        return try {
            gson.fromJson(value, listType)
        } catch (e: JsonSyntaxException) {
            HashMap()
        }
    }
}