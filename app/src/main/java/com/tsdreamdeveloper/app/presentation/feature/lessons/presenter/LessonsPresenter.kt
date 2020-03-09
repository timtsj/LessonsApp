package com.tsdreamdeveloper.app.presentation.feature.lessons.presenter

import com.tsdreamdeveloper.app.domain.usecase.lessons.GetAllLessonsUseCase
import com.tsdreamdeveloper.app.domain.usecase.lessons.ObserveAllLessonsUserCase
import com.tsdreamdeveloper.app.presentation.base.BasePresenter
import com.tsdreamdeveloper.app.presentation.feature.lessons.view.LessonsView
import com.tsdreamdeveloper.app.presentation.mapper.LessonsViewMapper
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class LessonsPresenter @Inject constructor(
    private val lessonsViewMapper: LessonsViewMapper,
    private val getLessonsUseCase: GetAllLessonsUseCase,
    private val observeAllLessonsUserCase: ObserveAllLessonsUserCase
) : BasePresenter<LessonsView>() {

    companion object {
        const val MAX_PAGE = 10
        const val MIN_PAGE = 1
    }

    private var currentPage: Int = 1

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        observeLessons()
    }

    private fun observeLessons() {
        observeAllLessonsUserCase.execute(ObserveAllLessonsUserCase.Param)
            .map {
                lessonsViewMapper.toView(it)
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnNext {
                viewState.displayLessons(it)
            }
            .subscribe({
            }, {
                errorHandler.handleError(it)
                viewState.handleError()
            })
            .also { disposables.add(it) }
    }

    fun getLessons(page: Int) {
        currentPage = page
        getLessonsUseCase.execute(GetAllLessonsUseCase.Param(page))
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSubscribe { viewState.showLoader() }
            .doAfterTerminate { viewState.hideLoader() }
            .subscribe({
                viewState.showNextButton(page < MAX_PAGE)
                viewState.showPrevButton(page > MIN_PAGE)
            }, {
                errorHandler.handleError(it)
            })
            .also { disposables.add(it) }
    }

    fun onRefresh() {
        getLessons(currentPage)
    }

    fun onNext() {
        getLessons(++currentPage)
    }

    fun onPrev() {
        getLessons(--currentPage)
    }
}