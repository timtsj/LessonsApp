package com.tsdreamdeveloper.app.utils.delegate

import android.content.res.Resources
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceDelegate @Inject constructor(
    private val res: Resources
) {

    fun getString(@StringRes id: Int): String = res.getString(id)

    fun getListString(@ArrayRes id: Int): Array<String> = res.getStringArray(id)
}