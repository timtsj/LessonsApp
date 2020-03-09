package com.tsdreamdeveloper.app.domain.usecase.lessons

import com.tsdreamdeveloper.app.domain.entity.LessonEntity
import com.tsdreamdeveloper.app.domain.repository.LessonRepo
import com.tsdreamdeveloper.app.domain.usecase.base.ObservableUseCase
import javax.inject.Inject

class ObserveAllLessonsUserCase @Inject constructor(
    private val lessonRepo: LessonRepo
) : ObservableUseCase<ObserveAllLessonsUserCase.Param, List<LessonEntity>>() {

    override fun build(parameters: Param) = lessonRepo.observeLessons()

    object Param
}