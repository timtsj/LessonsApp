package com.tsdreamdeveloper.app.presentation.base

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CanHandleError : MvpView, CanShowMessage {
    fun handleError() {}
}