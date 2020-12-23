package com.google.developers.teacup.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors



private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()

fun executeThread(f: () -> Unit) {
    SINGLE_EXECUTOR.execute(f)
}

fun Long.toDateTimeString(): String{
    val dtFormat = SimpleDateFormat("MMM d Y, h:mm a", Locale.getDefault())
    return dtFormat.format(this)
}
