package com.tsdreamdeveloper.app.presentation.feature.main.view

import com.tsdreamdeveloper.app.presentation.base.CanHandleError
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView, CanHandleError