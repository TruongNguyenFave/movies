package com.madison.client.appname.extention.helper.dateutils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DATE_FORMAT_DDMMYYYY = "dd-MM-yyyy'"


/**
 * Convert one date format string  to another date format string in android
 * @param inputDateFormat Input SimpleDateFormat
 * @param outputDateFormat Output SimpleDateFormat
 * @param inputDate  input Date String
 * @return
 * @throws ParseException
 */
@Throws(ParseException::class)
fun formatDateFromDateString(
    inputDateFormat: String,
    outputDateFormat: String,
    inputDate: String
): String {
    val parsedDate: Date?
    val outputDateString: String
    val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
    val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
    parsedDate = mInputDateFormat.parse(inputDate)
    parsedDate?.let {
        outputDateString = mOutputDateFormat.format(parsedDate)
        return outputDateString
    }

    return ""
}

@Throws(ParseException::class)
fun formatDateFromDateStringFromUTC(
    inputDateFormat: String,
    outputDateFormat: String,
    inputDate: String
): String {
    val mParsedDate: Date?
    val mOutputDateString: String
    val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
    mInputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
    mOutputDateFormat.timeZone = TimeZone.getDefault()
    mParsedDate = mInputDateFormat.parse(inputDate)
    mParsedDate?.let {
        mOutputDateString = mOutputDateFormat.format(mParsedDate)
        return mOutputDateString
    }
    return ""
}

fun convertStringToDateTime(dateString: String, format: String): Date {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.parse(dateString)
}

fun setDate(date: Int, month: Int, year: Int): Date {
    val calendar: Calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DATE, date)
    println(calendar.time)
    return calendar.time
}

fun dateToString(date: Date, format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(date)
}

fun getMonthString(month: Int): String {
    return when (month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        else -> "December"
    }
}