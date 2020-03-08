package com.tsdreamdeveloper.app.presentation.mapper

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.data.network.exception.BusinessLogicException
import com.tsdreamdeveloper.app.domain.entity.LessonEntity
import com.tsdreamdeveloper.app.domain.entity.OfflineMaterialEntity
import com.tsdreamdeveloper.app.domain.entity.SurveyEntity
import com.tsdreamdeveloper.app.domain.entity.VideoEntity
import com.tsdreamdeveloper.app.presentation.base.adapter.model.IAdapterItem
import com.tsdreamdeveloper.app.presentation.model.LessonDvo
import javax.inject.Inject

class LessonsViewMapper @Inject constructor(
    val context: Context
) {

    fun toView(list: List<LessonEntity>): List<IAdapterItem> = list.asSequence()
        .map {
            when (it) {
                is VideoEntity -> toView(it)
                is SurveyEntity -> toView(it)
                is OfflineMaterialEntity -> toView(it)
                else -> BusinessLogicException("Entity unsupported")
            }
        }.toList() as List<IAdapterItem>

    fun toView(from: VideoEntity) = LessonDvo(
        id = from.id,
        title = from.title,
        visited = from.visited,
        image = from.image,
        icon = R.drawable.ic_play_arrow_black_24dp,
        subTitle = "${from.commentsCount} Comments",
        subTitleIcon = R.drawable.ic_textsms_24px,
        subIconText = ""
    )

    fun toView(from: SurveyEntity) = LessonDvo(
        id = from.id,
        title = from.title,
        visited = from.visited,
        image = ColorDrawable(ContextCompat.getColor(context, R.color.pink_900)),
        icon = R.drawable.ic_quiz,
        subTitle = "${from.questionsCount} Questions",
        subTitleIcon = R.drawable.ic_insert_drive_file_24px,
        subIconText = "QUIZ"
    )

    fun toView(from: OfflineMaterialEntity) = LessonDvo(
        id = from.id,
        title = from.title,
        visited = from.visited,
        image = ColorDrawable(ContextCompat.getColor(context, R.color.blue_grey_700)),
        icon = R.drawable.ic_pdf,
        subTitle = from.format,
        subTitleIcon = R.drawable.ic_insert_drive_file_24px,
        subIconText = "OFFLINE MATERIAL"
    )
}