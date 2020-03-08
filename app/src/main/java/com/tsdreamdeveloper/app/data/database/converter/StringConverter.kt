package com.tsdreamdeveloper.app.data.database.converter

import androidx.room.TypeConverter

class StringConverter {

    @TypeConverter
    fun fromArray(strings: List<String>): String {
        var string = ""
        for (s in strings) string += ("$s,")
        return string
    }

    @TypeConverter
    fun toArray(concatenatedStrings: String): List<String> {
        val strings = mutableListOf<String>()
        for (s in concatenatedStrings.split(",")) strings.add(s)
        return strings
    }
}