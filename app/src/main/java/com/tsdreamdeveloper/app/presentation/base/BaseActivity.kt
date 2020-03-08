package com.tsdreamdeveloper.app.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.tsdreamdeveloper.app.presentation.widget.OnSwipeTouchListener
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    abstract var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        viewCreated()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = supportFragmentInjector

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewCreated()

    protected fun initListenSwipe(view: View) {
        view.setOnTouchListener(object : OnSwipeTouchListener(baseContext) {
            override fun onSwipeRight() {
                swipeBack()
            }
        })
    }

    protected open fun swipeBack() {}
}
