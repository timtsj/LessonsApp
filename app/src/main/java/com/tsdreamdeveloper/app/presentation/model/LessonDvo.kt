package com.tsdreamdeveloper.app.presentation.model

import com.tsdreamdeveloper.app.presentation.base.adapter.model.IAdapterItem

data class LessonDvo(
    val id: Int,
    val title: String,
    val visited: Boolean,
    val image: Any,
    val icon: Int,
    val subIconText: String,
    val subTitle: String,
    val subTitleIcon: Int
) : IAdapterItem