package com.tsdreamdeveloper.app.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.tsdreamdeveloper.app.presentation.widget.OnSwipeTouchListener
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes(), container, false)
    }

    protected fun initListenSwipe(view: View) {
        view.setOnTouchListener(object : OnSwipeTouchListener(context) {
            override fun onSwipeRight() {
                swipeRight()
            }

            override fun onSwipeLeft() {
                swipeLeft()
            }
        })
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected open fun swipeRight() {}

    protected open fun swipeLeft() {}
}
