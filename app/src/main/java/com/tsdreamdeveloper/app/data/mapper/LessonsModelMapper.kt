package com.tsdreamdeveloper.app.data.mapper

import com.tsdreamdeveloper.app.data.network.exception.ResponseException
import com.tsdreamdeveloper.app.data.network.model.LessonModel
import com.tsdreamdeveloper.app.domain.entity.LessonEntity
import javax.inject.Inject

class LessonsModelMapper @Inject constructor(
    private val surveyModelMapper: SurveyModelMapper,
    private val videoModelMapper: VideoModelMapper,
    private val offlineMaterialModelMapper: OfflineMaterialModelMapper
) {

    fun toEntity(list: List<LessonModel>): List<LessonEntity> = list.asSequence()
        .map {
            when (it.kind) {
                "Survey" -> surveyModelMapper.toEntity(it.item)
                "Video" -> videoModelMapper.toEntity(it.item)
                "OfflineMaterial" -> offlineMaterialModelMapper.toEntity(it.item)
                else -> throw ResponseException("Kind unsupported")
            }
        }.toList()
}