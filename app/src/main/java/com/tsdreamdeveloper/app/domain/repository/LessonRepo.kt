package com.tsdreamdeveloper.app.domain.repository

import com.tsdreamdeveloper.app.domain.entity.LessonEntity
import io.reactivex.Observable
import io.reactivex.Single

interface LessonRepo {

    fun getLessonsByPage(page: Int): Single<List<LessonEntity>>

    fun observeLessons(): Observable<List<LessonEntity>>
}