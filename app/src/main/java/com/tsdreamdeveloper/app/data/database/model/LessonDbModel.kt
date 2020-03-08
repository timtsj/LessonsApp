package com.tsdreamdeveloper.app.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tsdreamdeveloper.app.domain.entity.LessonEntity

open class LessonDbModel(
    open val id: Int,
    open val title: String,
    open val about: String,
    open val visited: Boolean
)

@Entity
data class SurveyDbModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val about: String,
    val visited: Boolean,
    val questionsCount: Int
)

@Entity
data class OfflineMaterialDbModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val about: String,
    val visited: Boolean,
    val fileExtension: String,
    val format: String
)

@Entity
data class VideoDbModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val about: String,
    val visited: Boolean,
    val commentsCount: Int,
    val description: String,
    val duration: Int,
    val image: String
)