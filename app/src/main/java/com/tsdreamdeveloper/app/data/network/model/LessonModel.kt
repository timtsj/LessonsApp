package com.tsdreamdeveloper.app.data.network.model

import com.google.gson.annotations.SerializedName

data class LessonModel(
    @SerializedName("kind")
    val kind: String?, // Survey, Video, OfflineMaterial
    @SerializedName("item")
    val item: ItemModel?
)

data class ItemModel(
    @SerializedName("id")
    val id: Int?, // 7
    @SerializedName("title")
    val title: String?, // Voluptatem soluta quisquam ut sit.
    @SerializedName("about")
    val about: String?, // Dolorem sit quam labore repellat fugit quia.
    @SerializedName("questions_count")
    val questionsCount: Int?, // 0
    @SerializedName("visited")
    val visited: Boolean?, // true
    @SerializedName("file_extension")
    val fileExtension: String?, // pdf
    @SerializedName("format")
    val format: String?, // pdf
    @SerializedName("comments_count")
    val commentsCount: Int?, // 2
    @SerializedName("description")
    val description: String?, // Video 1 description
    @SerializedName("duration")
    val duration: Int?, // 123
    @SerializedName("images")
    val images: ImagesModel?
)

data class ImagesModel(
    @SerializedName("large")
    val large: String?, // https://i.vimeocdn.com/video/812774707_960x540.jpg
    @SerializedName("medium")
    val medium: String?, // https://i.vimeocdn.com/video/812774707_960x540.jpg
    @SerializedName("small")
    val small: String?, // https://i.vimeocdn.com/video/812774707_960x540.jpg
    @SerializedName("xlarge")
    val xlarge: String?, // https://i.vimeocdn.com/video/812774707_960x540.jpg
    @SerializedName("xsmall")
    val xsmall: String?, // https://i.vimeocdn.com/video/812774707_960x540.jpg
    @SerializedName("xxlarge")
    val xxlarge: String? // https://i.vimeocdn.com/video/812774707_960x540.jpg
)