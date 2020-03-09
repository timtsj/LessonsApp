package com.tsdreamdeveloper.app.presentation.feature.lessons.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.DefaultItemAnimator
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.presentation.base.BaseFragment
import com.tsdreamdeveloper.app.presentation.base.adapter.model.IAdapterItem
import com.tsdreamdeveloper.app.presentation.base.adapter.regular.CompositeAdapter
import com.tsdreamdeveloper.app.presentation.feature.lessons.adapter.LessonsAdapter
import com.tsdreamdeveloper.app.presentation.feature.lessons.presenter.LessonsPresenter
import com.tsdreamdeveloper.app.utils.delegate.SnackbarDelegate
import com.tsdreamdeveloper.app.utils.delegate.SnackbarType
import com.tsdreamdeveloper.app.utils.ext.argument
import com.tsdreamdeveloper.app.utils.ext.getItems
import com.tsdreamdeveloper.app.utils.ext.setItems
import com.tsdreamdeveloper.app.utils.ext.setVisible
import kotlinx.android.synthetic.main.fragment_lessons.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class LessonsFragment : BaseFragment(), LessonsView {

    companion object {
        private const val PAGE_EXTRA = "PAGE_EXTRA"
        fun newInstance(page: Int) = LessonsFragment().apply {
            arguments = Bundle().apply {
                putInt(PAGE_EXTRA, page)
            }
        }
    }

    private val page: Int by argument(PAGE_EXTRA, 1)

    @Inject
    lateinit var providerPresenter: Provider<LessonsPresenter>

    @InjectPresenter
    lateinit var presenter: LessonsPresenter

    @ProvidePresenter
    fun providePresenter(): LessonsPresenter = providerPresenter.get()

    @Inject
    lateinit var snackbarDelegate: SnackbarDelegate

    override fun layoutRes(): Int = R.layout.fragment_lessons

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        val adapter = CompositeAdapter.Builder()
            .add(LessonsAdapter())
            .build()

        fragment_lessons_list.apply {
            this.adapter = adapter
            itemAnimator = DefaultItemAnimator()
        }
        presenter.getLessons(page)
        fragment_lessons_retry.setOnClickListener { presenter.onRefresh() }
        fragment_lessons_refresh.setOnRefreshListener { presenter.onRefresh() }
        fragment_lessons_next.setOnClickListener { presenter.onNext() }
        fragment_lessons_prev.setOnClickListener { presenter.onPrev() }
    }

    override fun displayLessons(items: List<IAdapterItem>) {
        fragment_lessons_list.setItems(items)
    }

    override fun showLoader() {
        fragment_lessons_refresh.isRefreshing = true
    }

    override fun hideLoader() {
        fragment_lessons_refresh.isRefreshing = false
    }

    override fun showMessage(text: String, snackbarType: SnackbarType) {
        snackbarDelegate.showMessage(snackbarType, text)
    }

    override fun handleError() {
        fragment_lessons_retry.setVisible(fragment_lessons_list.getItems<IAdapterItem>().isEmpty())
        showNextButton(false)
        showPrevButton(false)
    }

    override fun showNextButton(isVisible: Boolean) {
        fragment_lessons_next.setVisible(isVisible)
    }

    override fun showPrevButton(isVisible: Boolean) {
        fragment_lessons_prev.setVisible(isVisible)
    }
}