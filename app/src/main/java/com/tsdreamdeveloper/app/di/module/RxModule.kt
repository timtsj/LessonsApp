package com.tsdreamdeveloper.app.di.module

import com.tsdreamdeveloper.app.di.PerApplication
import com.tsdreamdeveloper.app.utils.schedulers.AppSchedulersProvider
import com.tsdreamdeveloper.app.utils.schedulers.SchedulersProvider
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable

@Module
class RxModule {

    @PerApplication
    @Provides
    fun provideSchedulers(): SchedulersProvider = AppSchedulersProvider()

    @Provides
    @NonNull
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}