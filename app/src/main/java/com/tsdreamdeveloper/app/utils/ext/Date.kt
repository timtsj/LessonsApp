package com.tsdreamdeveloper.app.utils.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.US)
    return formatter.format(this)
}

fun String.formatDate(format: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): Date {
    val formatSimple = SimpleDateFormat(format, Locale.US)
    println()
    return formatSimple.parse(this)
}

fun Long.formatDate(): String {
    val cal = Calendar.getInstance()
    cal.timeInMillis = this * 1000
    return cal.time.formatDate("dd.MM.yyyy")
}

