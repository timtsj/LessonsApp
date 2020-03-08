package com.tsdreamdeveloper.app.domain.entity

open class LessonEntity(
    open val id: Int,
    open val title: String,
    open val about: String,
    open val visited: Boolean
)

data class SurveyEntity(
    override val id: Int,
    override val title: String,
    override val about: String,
    override val visited: Boolean,
    val questionsCount: Int
) : LessonEntity(id, title, about, visited)

data class OfflineMaterialEntity(
    override val id: Int,
    override val title: String,
    override val about: String,
    override val visited: Boolean,
    val fileExtension: String,
    val format: String
) : LessonEntity(id, title, about, visited)

data class VideoEntity(
    override val id: Int,
    override val title: String,
    override val about: String,
    override val visited: Boolean,
    val commentsCount: Int,
    val description: String,
    val duration: Int,
    val image: String
) : LessonEntity(id, title, about, visited)