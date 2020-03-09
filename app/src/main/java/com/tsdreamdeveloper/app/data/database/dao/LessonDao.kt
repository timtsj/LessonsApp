package com.tsdreamdeveloper.app.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.tsdreamdeveloper.app.data.database.model.OfflineMaterialDbModel
import com.tsdreamdeveloper.app.data.database.model.SurveyDbModel
import com.tsdreamdeveloper.app.data.database.model.VideoDbModel
import io.reactivex.Observable

@Dao
abstract class SurveyDao : CommonBaseDao<SurveyDbModel>() {

    @Query("SELECT * FROM SurveyDbModel")
    abstract fun observeModel(): Observable<List<SurveyDbModel>>
}

@Dao
abstract class VideoDao : CommonBaseDao<VideoDbModel>() {
    @Query("SELECT * FROM VideoDbModel")
    abstract fun observeModel(): Observable<List<VideoDbModel>>
}

@Dao
abstract class OfflineMaterialDao : CommonBaseDao<OfflineMaterialDbModel>() {
    @Query("SELECT * FROM OfflineMaterialDbModel")
    abstract fun observeModel(): Observable<List<OfflineMaterialDbModel>>
}