package com.tsdreamdeveloper.app.presentation.base

import com.tsdreamdeveloper.app.utils.ErrorHandler
import com.tsdreamdeveloper.app.utils.schedulers.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView
import javax.inject.Inject

abstract class BasePresenter<View> : MvpPresenter<View>() where View : MvpView, View : CanHandleError {

    @Inject
    lateinit var disposables: CompositeDisposable

    @Inject
    lateinit var schedulers: SchedulersProvider

    @Inject
    lateinit var errorHandler: ErrorHandler

    override fun attachView(view: View?) {
        super.attachView(view)
        errorHandler.attachView(viewState)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}