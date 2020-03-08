package com.tsdreamdeveloper.app.di.module

import android.content.Context
import androidx.room.Room
import com.tsdreamdeveloper.app.data.database.AppDatabase
import com.tsdreamdeveloper.app.di.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @PerApplication
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    @PerApplication
    @Provides
    fun provideSurveyDao(db: AppDatabase) = db.getSurveyDao()

    @PerApplication
    @Provides
    fun provideVideoDao(db: AppDatabase) = db.getVideoDao()

    @PerApplication
    @Provides
    fun provideOfflineMaterialDao(db: AppDatabase) = db.getOfflineMaterialDao()
}