package com.tsdreamdeveloper.app.utils.ext

import java.lang.ref.WeakReference

fun <T> T.weak() = WeakReference(this)