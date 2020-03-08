package com.tsdreamdeveloper.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tsdreamdeveloper.app.data.database.converter.DateConverter
import com.tsdreamdeveloper.app.data.database.converter.HashMapConverter
import com.tsdreamdeveloper.app.data.database.converter.IntConverter
import com.tsdreamdeveloper.app.data.database.converter.StringConverter
import com.tsdreamdeveloper.app.data.database.dao.OfflineMaterialDao
import com.tsdreamdeveloper.app.data.database.dao.SurveyDao
import com.tsdreamdeveloper.app.data.database.dao.VideoDao
import com.tsdreamdeveloper.app.data.database.model.OfflineMaterialDbModel
import com.tsdreamdeveloper.app.data.database.model.SurveyDbModel
import com.tsdreamdeveloper.app.data.database.model.VideoDbModel

@Database(
    entities = [
        SurveyDbModel::class,
        VideoDbModel::class,
        OfflineMaterialDbModel::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(
    IntConverter::class,
    HashMapConverter::class,
    DateConverter::class,
    StringConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "AppDatabase"
    }

    abstract fun getSurveyDao(): SurveyDao

    abstract fun getVideoDao(): VideoDao

    abstract fun getOfflineMaterialDao(): OfflineMaterialDao

    fun clear() {
        getSurveyDao().deleteAll()
        getVideoDao().deleteAll()
        getOfflineMaterialDao().deleteAll()
    }
}