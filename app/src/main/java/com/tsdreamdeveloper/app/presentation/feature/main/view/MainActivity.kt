package com.tsdreamdeveloper.app.presentation.feature.main.view

import androidx.annotation.LayoutRes
import com.tsdreamdeveloper.app.R
import com.tsdreamdeveloper.app.presentation.base.BaseActivity
import com.tsdreamdeveloper.app.presentation.feature.main.presenter.MainPresenter
import com.tsdreamdeveloper.app.utils.delegate.SnackbarType
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : BaseActivity(), MainView {

    companion object {
        const val ID_CONTAINER: Int = R.id.container
    }

    @Inject
    lateinit var provider: Provider<MainPresenter>

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = provider.get()

    @Inject
    override lateinit var navigator: Navigator

    @LayoutRes
    override fun layoutRes() = R.layout.activity_main

    override fun viewCreated() {}

    override fun showMessage(text: String, snackbarType: SnackbarType) {}

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}