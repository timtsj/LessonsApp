package com.tsdreamdeveloper.app.data.network

import com.tsdreamdeveloper.app.data.network.model.LessonModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LessonApi {

    @GET("api/tests/lessons")
    fun getLessons(@Query("page") page: Int): Single<List<LessonModel>>
}