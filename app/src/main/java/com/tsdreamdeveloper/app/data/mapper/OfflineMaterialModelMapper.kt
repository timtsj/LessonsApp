package com.tsdreamdeveloper.app.data.mapper

import com.tsdreamdeveloper.app.data.database.model.OfflineMaterialDbModel
import com.tsdreamdeveloper.app.data.network.model.ItemModel
import com.tsdreamdeveloper.app.domain.entity.OfflineMaterialEntity
import javax.inject.Inject

class OfflineMaterialModelMapper @Inject constructor() {

    fun toEntity(from: ItemModel?) = OfflineMaterialEntity(
        id = from?.id ?: 0,
        title = from?.title ?: "",
        about = from?.about ?: "",
        visited = from?.visited ?: false,
        fileExtension = from?.fileExtension ?: "",
        format = from?.format ?: ""
    )

    fun toEntity(list: List<ItemModel>) =
        list.asSequence()
            .map { from ->
                OfflineMaterialEntity(
                    id = from.id ?: 0,
                    title = from.title ?: "",
                    about = from.about ?: "",
                    visited = from.visited ?: false,
                    fileExtension = from.fileExtension ?: "",
                    format = from.format ?: ""
                )
            }.toList()

    fun toEntityFromDb(from: OfflineMaterialDbModel) = OfflineMaterialEntity(
        id = from.id,
        title = from.title,
        about = from.about,
        visited = from.visited,
        fileExtension = from.fileExtension,
        format = from.format
    )

    fun toEntityFromDb(list: List<OfflineMaterialDbModel>) =
        list.map { from ->
            OfflineMaterialEntity(
                id = from.id,
                title = from.title,
                about = from.about,
                visited = from.visited,
                fileExtension = from.fileExtension,
                format = from.format
            )
        }

    fun toDb(from: OfflineMaterialEntity) = OfflineMaterialDbModel(
        id = from.id,
        title = from.title,
        about = from.about,
        visited = from.visited,
        fileExtension = from.fileExtension,
        format = from.format
    )

    fun toDb(list: List<OfflineMaterialEntity>) =
        list.map { from ->
            OfflineMaterialDbModel(
                id = from.id,
                title = from.title,
                about = from.about,
                visited = from.visited,
                fileExtension = from.fileExtension,
                format = from.format
            )
        }
}