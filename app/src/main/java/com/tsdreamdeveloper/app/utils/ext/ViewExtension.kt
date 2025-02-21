package com.tsdreamdeveloper.app.utils.ext

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.tsdreamdeveloper.app.presentation.base.adapter.base.BaseCompositeDelegateAdapter

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.hide() {
    setVisibleOrGone(false)
}

fun View.show() {
    setVisibleOrGone(true)
}

fun View.fadeIn(duration: Long = 300) {
    animate().alpha(1F).duration = duration
}

fun View.fadeOut(duration: Long = 300) {
    animate().alpha(0F).duration = duration
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}

fun <T> RecyclerView.setItems(items: List<T>) {
    if (adapter !is BaseCompositeDelegateAdapter<*>) {
        return
    }

    (adapter as BaseCompositeDelegateAdapter<T>).setItems(items)
}

fun <T> RecyclerView.getItems(): List<T> {
    if (adapter !is BaseCompositeDelegateAdapter<*>) {
        return listOf()
    }

    return (adapter as BaseCompositeDelegateAdapter<T>).getItems()
}