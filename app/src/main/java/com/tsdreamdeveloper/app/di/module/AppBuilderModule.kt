package com.tsdreamdeveloper.app.di.module

import com.tsdreamdeveloper.app.di.PerActivity
import com.tsdreamdeveloper.app.presentation.feature.main.di.MainViewModule
import com.tsdreamdeveloper.app.presentation.feature.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainViewModule::class])
    fun provideMainActivityFactory(): MainActivity
}