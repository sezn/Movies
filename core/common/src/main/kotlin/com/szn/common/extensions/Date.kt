package com.szn.common.extensions

import java.text.SimpleDateFormat
import java.util.*

val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
val sdfEntry = SimpleDateFormat("yyyy-MM-dd")
val sdfYear = SimpleDateFormat("yyyy")

fun now(): String {
    return sdf.format(Calendar.getInstance().time)
}

fun toYear(d: String): String{
    return try {
        sdfYear.format(sdfEntry.parse(d))
    } catch (e: Exception){
        "Null"
    }
}

fun delayFromDate(d: Date): String {
    val now = Date()
    val secs = (now.time - d.time) / 1000
    var hours = secs / 3600
    val minutes = secs % 3600 / 60
    val seconds = secs % 60
    val days = hours / 24
    if(days > 0)
        hours -= days * 24

    return if(days > 0){
        String.format("%dj %dh%dm", days, hours, minutes)
    }else if (hours > 0) {
        String.format("%dh %dm", hours, minutes)
    } else if (minutes > 0) {
        if (seconds > 0) String.format("%dm%ds", minutes, seconds) else String.format("%dm", minutes)
    } else if(seconds > 0){
        String.format("%ds", seconds)
    } else {
        ""
    }
}