@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.madison.client.appname.extention.helper.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by GianhTran on 13/06/2019.
 * Email: gianhtns.bk@gmail.com
 */

const val EVENT_TIME_FORMAT = "MMM dd, yyyy"
const val ANALYTICS_FORMAT = "MMMM dd"
const val ANALYTICS_WITH_YEAR_FORMAT = "MMMM dd, yyyy"
const val ANALYTICS_NO_MONTH_WITH_YEAR_FORMAT = "dd, yyyy"
const val DAY_OF_WEEK_FORMAT = "EEEE"
const val BIRTH_DAY_FORMAT = "dd MMMM yyyy"
const val NEO_4J_DATE_FORMAT = "yyyy-MM-dd"
const val NEO_4J_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val EVENT_TIME_SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val MESSAGE_GROUP_FORMAT = "MMM d, yyyy"
const val SCHEDULE_FORMAT = "h:mm a"
const val CALENDAR_EVENT_TIME_FORMAT = "dd MMM"
const val CHAT_DATE_ID_FORMAT = "yyyyMMdd"
const val EVENT_INFO_TIME_FORMAT = "E, MMM dd yyyy-h:mm a"

private const val NEO_4J_DATETIME_FORMAT = "YYYY-MM-DD HH:mm:ss"
private const val EVENT_TIME_FORMAT_SIMPLIFY = "MMM dd"
private const val YEAR_FORMAT = "yyyy"
private const val ORDER_DAY_FORMAT = "MMMM dd, yyyy HH:mm (z)"
private const val HISTORY_DAY_FORMAT = "dd MMM yyyy, hh:mma"
private const val CARD_EXPIRED_SERVER_FORMAT = "MM/yyyy"
private const val CARD_EXPIRED_UI_FORMAT = "MMMM yyyy"

fun isSameYear(startDay: Long, endDay: Long): Boolean {
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()
    cal1.timeInMillis = startDay
    cal2.timeInMillis = endDay
    return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
}

fun isSameMonth(startDay: Long, endDay: Long): Boolean {
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()
    cal1.timeInMillis = startDay
    cal2.timeInMillis = endDay
    return (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
}

/**
 * Convert one date format string  to another date format string in android
 * @param inputDateFormat Input SimpleDateFormat
 * @param outputDateFormat Output SimpleDateFormat
 * @param inputDate  input Date String
 * @return
 * @throws ParseException
 */
fun formatDateFromDateString(
    inputDateFormat: String,
    outputDateFormat: String,
    inputDate: String,
    locale: Locale = Locale.getDefault(),
    inputTimeZone: TimeZone? = null,
    outputTimeZone: TimeZone? = null
): String? {
    try {
        val mInputDateFormat = SimpleDateFormat(inputDateFormat, locale)
        inputTimeZone?.let {
            mInputDateFormat.timeZone = inputTimeZone
        }

        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, locale)
        outputTimeZone?.let {
            mOutputDateFormat.timeZone = outputTimeZone
        }

        mInputDateFormat.parse(inputDate)?.let {
            return mOutputDateFormat.format(it)
        }
    } catch (ex: ParseException) {
        return null
    }

    return null
}


fun convertStringToDateTime(dateString: String, format: String, timeZone: TimeZone? = null): Date? {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    timeZone?.let {
        sdf.timeZone = timeZone
    }
    return try {
        val date = sdf.parse(dateString)
        date
    } catch (ex: ParseException) {
        null
    }
}

fun convertDateToString(
    date: Date, format: String, timeZone: TimeZone? = null, locale: Locale = Locale.getDefault()
): String? {
    val sdf = SimpleDateFormat(format, locale)
    timeZone?.let {
        sdf.timeZone = timeZone
    }

    return try {
        sdf.format(date)
    } catch (ex: ParseException) {
        null
    }
}

fun getDateFromLong(
    milliSeconds: Long,
    dateFormat: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone? = null
): String {
    val formatter = SimpleDateFormat(dateFormat, locale)

    timeZone?.let {
        formatter.timeZone = timeZone
    }

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}

fun getGMTTimeZone(): TimeZone {
    return TimeZone.getTimeZone("GMT")
}

fun diff(time: Long, field: Int): Long {
    val fieldTime = getFieldInMillis(field)
    val cal = Calendar.getInstance()
    val now = cal.timeInMillis
    return time / fieldTime - now / fieldTime
}

private fun getFieldInMillis(field: Int): Long {
    val cal = Calendar.getInstance()
    val now = cal.timeInMillis
    cal.add(field, 1)
    val after = cal.timeInMillis
    return after - now
}


fun getDateFromPicker(
    day: Int,
    month: Int,
    year: Int,
    format: String,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.getDefault()
): String {

    val calendar = Calendar.getInstance()
    calendar[year, month] = day

    val sdf = SimpleDateFormat(format, locale)
    timeZone?.let {
        sdf.timeZone = timeZone
    }
    return sdf.format(calendar.time)
}