package com.tsdreamdeveloper.app.utils.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.toggleKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun AppCompatActivity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    val view = currentFocus
    view?.let {
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun postDelay(time: Long = 200, action: () -> Unit) {
    Handler().postDelayed(action, time)
}

fun String.openInBrowser(context: Context?) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(this))
    context?.startActivity(browserIntent)
}