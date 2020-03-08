package com.tsdreamdeveloper.app.data.repository

import com.tsdreamdeveloper.app.data.mapper.LessonsModelMapper
import com.tsdreamdeveloper.app.data.network.LessonApi
import com.tsdreamdeveloper.app.domain.entity.LessonEntity
import com.tsdreamdeveloper.app.domain.repository.LessonRepo
import io.reactivex.Single
import javax.inject.Inject

class LessonRepoImpl @Inject constructor(
    private val lessonApi: LessonApi,
    private val lessonsModelMapper: LessonsModelMapper
) : LessonRepo {

    override fun getLessonsByPage(page: Int): Single<List<LessonEntity>> {
        return lessonApi.getLessons(page).map {
            lessonsModelMapper.toEntity(it)
        }
    }
}