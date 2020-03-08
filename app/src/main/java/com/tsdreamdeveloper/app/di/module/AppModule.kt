package com.tsdreamdeveloper.app.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.tsdreamdeveloper.app.App
import com.tsdreamdeveloper.app.di.PerApplication
import com.tsdreamdeveloper.app.utils.delegate.ResourceDelegate
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class AppModule {

    @PerApplication
    @Provides
    fun provideContext(application: App): Context = application

    @PerApplication
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @PerApplication
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

    @PerApplication
    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @PerApplication
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @PerApplication
    fun provideResourceDelegate(context: Context) = ResourceDelegate(context.resources)
}