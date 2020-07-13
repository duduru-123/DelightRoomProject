package com.delightroom.android.gitproject.utility

import android.annotation.SuppressLint
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*


/**
 * @return date string transformed by format
 */
@SuppressLint("SimpleDateFormat")
fun getDateFormat(time: Long, format: String): String {
    val date = Date(time)
    val dateFormat = SimpleDateFormat(format)

    return dateFormat.format(date)
}


/**
 * convert time(string) to millis
 */
fun getDateMillis(time: String) =
    DateTime(time).millis


/**
 * date format YYYY MM dd
 * @param[time]
 */
fun getDateYYYYMMdd(time: Long): String {
    val format = "YYYY MM dd"
    return getDateFormat(time, format)
}