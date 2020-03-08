package com.tsdreamdeveloper.app.presentation.feature.lessons.view

import com.tsdreamdeveloper.app.presentation.base.CanHandleError
import com.tsdreamdeveloper.app.presentation.base.CanShowLoader
import com.tsdreamdeveloper.app.presentation.base.adapter.model.IAdapterComparableItem
import com.tsdreamdeveloper.app.presentation.base.adapter.model.IAdapterItem
import moxy.MvpView

interface LessonsView : MvpView, CanShowLoader, CanHandleError {

    fun displayLessons(items: List<IAdapterItem>)

    fun showNextButton(isVisible: Boolean)

    fun showPrevButton(isVisible: Boolean)
}