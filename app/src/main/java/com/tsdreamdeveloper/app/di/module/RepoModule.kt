package com.tsdreamdeveloper.app.di.module

import com.tsdreamdeveloper.app.data.repository.LessonRepoImpl
import com.tsdreamdeveloper.app.di.PerApplication
import com.tsdreamdeveloper.app.domain.repository.LessonRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    @PerApplication
    fun provideLessonRepoImpl(lessonRepoImpl: LessonRepoImpl): LessonRepo
}