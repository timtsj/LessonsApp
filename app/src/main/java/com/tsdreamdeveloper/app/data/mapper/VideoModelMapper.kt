package com.tsdreamdeveloper.app.data.mapper

import com.tsdreamdeveloper.app.data.database.model.VideoDbModel
import com.tsdreamdeveloper.app.data.network.model.ItemModel
import com.tsdreamdeveloper.app.domain.entity.VideoEntity
import javax.inject.Inject

class VideoModelMapper @Inject constructor() {

    fun toEntity(from: ItemModel?) = VideoEntity(
        id = from?.id ?: 0,
        title = from?.title ?: "",
        about = from?.about ?: "",
        visited = from?.visited ?: false,
        commentsCount = from?.commentsCount ?: 0,
        description = from?.description ?: "",
        duration = from?.duration ?: 0,
        image = from?.images?.xxlarge ?: ""
    )

    fun toEntity(list: List<ItemModel>) =
        list.asSequence()
            .map { from ->
                VideoEntity(
                    id = from.id ?: 0,
                    title = from.title ?: "",
                    about = from.about ?: "",
                    visited = from.visited ?: false,
                    commentsCount = from.commentsCount ?: 0,
                    description = from.description ?: "",
                    duration = from.duration ?: 0,
                    image = from.images?.xxlarge ?: ""
                )
            }.toList()

    fun toEntityFromDb(from: VideoDbModel) = VideoEntity(
        id = from.id,
        title = from.title,
        about = from.about,
        visited = from.visited,
        commentsCount = from.commentsCount,
        description = from.description,
        duration = from.duration,
        image = from.image
    )

    fun toEntityFromDb(list: List<VideoDbModel>) =
        list.map { from ->
            VideoEntity(
                id = from.id,
                title = from.title,
                about = from.about,
                visited = from.visited,
                commentsCount = from.commentsCount,
                description = from.description,
                duration = from.duration,
                image = from.image
            )
        }

    fun toDb(from: VideoEntity) = VideoDbModel(
        id = from.id,
        title = from.title,
        about = from.about,
        visited = from.visited,
        commentsCount = from.commentsCount,
        description = from.description,
        duration = from.duration,
        image = from.image
    )

    fun toDb(list: List<VideoEntity>) =
        list.map { from ->
            VideoDbModel(
                id = from.id,
                title = from.title,
                about = from.about,
                visited = from.visited,
                commentsCount = from.commentsCount,
                description = from.description,
                duration = from.duration,
                image = from.image
            )
        }
}