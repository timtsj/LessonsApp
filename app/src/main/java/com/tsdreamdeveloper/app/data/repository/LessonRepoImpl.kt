package com.tsdreamdeveloper.app.data.repository

import com.tsdreamdeveloper.app.data.database.dao.OfflineMaterialDao
import com.tsdreamdeveloper.app.data.database.dao.SurveyDao
import com.tsdreamdeveloper.app.data.database.dao.VideoDao
import com.tsdreamdeveloper.app.data.database.model.OfflineMaterialDbModel
import com.tsdreamdeveloper.app.data.database.model.SurveyDbModel
import com.tsdreamdeveloper.app.data.database.model.VideoDbModel
import com.tsdreamdeveloper.app.data.mapper.LessonsModelMapper
import com.tsdreamdeveloper.app.data.mapper.OfflineMaterialModelMapper
import com.tsdreamdeveloper.app.data.mapper.SurveyModelMapper
import com.tsdreamdeveloper.app.data.mapper.VideoModelMapper
import com.tsdreamdeveloper.app.data.network.LessonApi
import com.tsdreamdeveloper.app.domain.entity.LessonEntity
import com.tsdreamdeveloper.app.domain.entity.OfflineMaterialEntity
import com.tsdreamdeveloper.app.domain.entity.SurveyEntity
import com.tsdreamdeveloper.app.domain.entity.VideoEntity
import com.tsdreamdeveloper.app.domain.repository.LessonRepo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class LessonRepoImpl @Inject constructor(
    private val lessonApi: LessonApi,
    private val lessonsModelMapper: LessonsModelMapper,
    private val surveyModelMapper: SurveyModelMapper,
    private val videoModelMapper: VideoModelMapper,
    private val offlineMaterialModelMapper: OfflineMaterialModelMapper,
    private val surveyDao: SurveyDao,
    private val videoDao: VideoDao,
    private val offlineMaterialDao: OfflineMaterialDao
) : LessonRepo {

    override fun getLessonsByPage(page: Int): Single<List<LessonEntity>> {
        return lessonApi.getLessons(page).map {
            lessonsModelMapper.toEntity(it)
        }.doOnSuccess { list ->
            surveyDao.deleteAll()
            videoDao.deleteAll()
            offlineMaterialDao.deleteAll()
            list.map { entity ->
                when (entity) {
                    is SurveyEntity -> {
                        val model = surveyModelMapper.toDb(entity)
                        surveyDao.insertOrUpdate(model)
                    }
                    is VideoEntity -> {
                        val model = videoModelMapper.toDb(entity)
                        videoDao.insertOrUpdate(model)
                    }
                    is OfflineMaterialEntity -> {
                        val model = offlineMaterialModelMapper.toDb(entity)
                        offlineMaterialDao.insertOrUpdate(model)
                    }
                }
            }
        }
    }

    override fun observeLessons(): Observable<List<LessonEntity>> {
        return Observable.combineLatest(
            surveyDao.observeModel(),
            videoDao.observeModel(),
            offlineMaterialDao.observeModel(),
            Function3 { surveys: List<SurveyDbModel>,
                        videos: List<VideoDbModel>,
                        offlineMaterials: List<OfflineMaterialDbModel> ->
                mutableListOf<LessonEntity>().apply {
                    surveys.map {
                        add(surveyModelMapper.toEntityFromDb(it))
                    }
                    videos.map {
                        add(videoModelMapper.toEntityFromDb(it))
                    }
                    offlineMaterials.map {
                        add(offlineMaterialModelMapper.toEntityFromDb(it))
                    }
                }.shuffled()
            }
        )
    }
}