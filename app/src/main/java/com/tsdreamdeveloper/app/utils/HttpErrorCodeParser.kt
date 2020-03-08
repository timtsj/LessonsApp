package com.tsdreamdeveloper.app.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tsdreamdeveloper.app.di.PerApplication
import okhttp3.ResponseBody
import javax.inject.Inject

@PerApplication
class HttpErrorCodeParser @Inject constructor(
        private val gson: Gson
) {
    fun parseCode(errorBody: ResponseBody): String? {
        return try {
            val errorString = errorBody.string()
            gson.fromJson(errorString, JsonObject::class.java)
                    .asJsonObject.get("error_description")
                    .asString
        } catch (e: Exception) {
            null
        }
    }
}