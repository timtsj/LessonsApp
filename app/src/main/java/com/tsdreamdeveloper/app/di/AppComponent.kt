package com.tsdreamdeveloper.app.di

import com.tsdreamdeveloper.app.App
import com.tsdreamdeveloper.app.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AppModule::class,
        RxModule::class,
        NetworkModule::class,
        AppBuilderModule::class,
        AndroidSupportInjectionModule::class,
        RepoModule::class,
        DbModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}