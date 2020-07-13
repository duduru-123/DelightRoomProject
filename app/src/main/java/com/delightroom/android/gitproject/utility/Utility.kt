package com.delightroom.android.gitproject.utility

import android.annotation.SuppressLint
import android.content.res.Resources
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

/**
 * High-order function
 */

/**
 * dp to px
 * @return px calculated from dp
 */
val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Float.px get() = (this * Resources.getSystem().displayMetrics.density)


/**
 * px to dp
 * @return dp calculated from px
 */
val Int.dp get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Float.dp get() = (this / Resources.getSystem().displayMetrics.density)

