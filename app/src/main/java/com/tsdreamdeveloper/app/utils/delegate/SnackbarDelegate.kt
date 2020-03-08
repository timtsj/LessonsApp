package com.tsdreamdeveloper.app.utils.delegate

import android.app.Activity
import android.os.SystemClock
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.lang.ref.WeakReference

class SnackbarDelegate(private val activity: WeakReference<out Activity>) {

    private var lastClickTime: Long = 0

    fun showMessage(type: SnackbarType, message: String) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()

        activity.get()?.findViewById<View>(android.R.id.content)?.let {
            with(Snackbar.make(it, message, Snackbar.LENGTH_SHORT)) {
                setActionTextColor(androidx.core.content.ContextCompat.getColor(view.context, android.R.color.white))
                view.setBackgroundColor(androidx.core.content.ContextCompat.getColor(view.context, type.color))
                show()
            }
        } ?: throw IllegalArgumentException("Weak ref on activity is empty")
    }
}

enum class SnackbarType(val color: Int) {
    SUCCESS(android.R.color.holo_green_dark),
    ERROR(android.R.color.holo_red_dark),
}