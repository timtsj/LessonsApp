package com.tsdreamdeveloper.app.data.mapper

import com.tsdreamdeveloper.app.data.database.model.SurveyDbModel
import com.tsdreamdeveloper.app.data.network.model.ItemModel
import com.tsdreamdeveloper.app.domain.entity.SurveyEntity
import javax.inject.Inject

class SurveyModelMapper @Inject constructor() {

    fun toEntity(from: ItemModel?) = SurveyEntity(
        id = from?.id ?: 0,
        title = from?.title ?: "",
        about = from?.about ?: "",
        visited = from?.visited ?: false,
        questionsCount = from?.questionsCount ?: 0
    )

    fun toEntity(list: List<ItemModel>) =
        list.asSequence()
            .map { from ->
                SurveyEntity(
                    id = from.id ?: 0,
                    title = from.title ?: "",
                    about = from.about ?: "",
                    visited = from.visited ?: false,
                    questionsCount = from.questionsCount ?: 0
                )
            }.toList()

    fun toEntityFromDb(from: SurveyDbModel) = SurveyEntity(
        id = from.id,
        title = from.title,
        about = from.about,
        visited = from.visited,
        questionsCount = from.questionsCount
    )

    fun toEntityFromDb(list: List<SurveyDbModel>) =
        list.map { from ->
            SurveyEntity(
                id = from.id,
                title = from.title,
                about = from.about,
                visited = from.visited,
                questionsCount = from.questionsCount
            )
        }

    fun toDb(from: SurveyEntity) = SurveyDbModel(
        id = from.id,
        title = from.title,
        about = from.about,
        visited = from.visited,
        questionsCount = from.questionsCount
    )

    fun toDb(list: List<SurveyEntity>) =
        list.map { from ->
            SurveyDbModel(
                id = from.id,
                title = from.title,
                about = from.about,
                visited = from.visited,
                questionsCount = from.questionsCount
            )
        }
}