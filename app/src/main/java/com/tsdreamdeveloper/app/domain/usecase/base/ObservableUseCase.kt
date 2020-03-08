package com.tsdreamdeveloper.app.domain.usecase.base

import io.reactivex.Observable

abstract class ObservableUseCase<in PARAMS, RESULT> {

    fun execute(parameters: PARAMS) = build(parameters)

    protected abstract fun build(parameters: PARAMS): Observable<RESULT>
}