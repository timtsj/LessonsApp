package com.tsdreamdeveloper.app.domain.usecase.lessons

import com.tsdreamdeveloper.app.domain.entity.LessonEntity
import com.tsdreamdeveloper.app.domain.repository.LessonRepo
import com.tsdreamdeveloper.app.domain.usecase.base.SingleUseCase
import javax.inject.Inject

class GetAllLessonsUseCase @Inject constructor(
    private val lessonRepo: LessonRepo
) : SingleUseCase<GetAllLessonsUseCase.Param, List<LessonEntity>>() {

    override fun build(parameters: Param) = lessonRepo.getLessonsByPage(parameters.page)

    data class Param(val page: Int)
}