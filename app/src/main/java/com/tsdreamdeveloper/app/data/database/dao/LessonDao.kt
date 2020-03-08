package com.tsdreamdeveloper.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsdreamdeveloper.app.data.database.model.LessonDbModel
import com.tsdreamdeveloper.app.data.database.model.OfflineMaterialDbModel
import com.tsdreamdeveloper.app.data.database.model.SurveyDbModel
import com.tsdreamdeveloper.app.data.database.model.VideoDbModel

@Dao
abstract class SurveyDao : CommonBaseDao<SurveyDbModel>()

@Dao
abstract class VideoDao : CommonBaseDao<VideoDbModel>()

@Dao
abstract class OfflineMaterialDao : CommonBaseDao<OfflineMaterialDbModel>()