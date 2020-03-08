package com.tsdreamdeveloper.app.presentation.base

import com.tsdreamdeveloper.app.utils.delegate.SnackbarType
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CanShowMessage : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun showMessage(text: String, snackbarType: SnackbarType)
}