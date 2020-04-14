package com.madison.crisis.crisissuppervisor.extention.helper

import android.util.Pair

fun convertToMinutesSecondFormat(milliseconds: Long): String {
    val minutes = milliseconds / 1000 / 60
    val seconds = milliseconds / 1000 % 60
    if (seconds < 10) {
        return "$minutes:0$seconds"
    }
    return "$minutes:$seconds"
}

fun splitFirstAndLastName(fullName: String): Pair<String, String> {
    var firstName = ""
    var lastName = ""

    if (fullName.split(" ").size > 1) {
        lastName = fullName.substring(fullName.lastIndexOf(" ") + 1)
        firstName = fullName.substring(0, fullName.lastIndexOf(' '))
    } else {
        firstName = fullName
    }
    return Pair(firstName, lastName)
}

/**
 * input: example@gmail.com
 * output: exa****@gmail.com
 */
fun getHiddenEmail(email: String): String {
    val emailParts = email.split("@")
    var hiddenEmail = ""
    for (i in 0 until emailParts[0].length) {
        when (i) {
            0, 1, 2 -> hiddenEmail += emailParts[0][i]
            else -> hiddenEmail += "*"
        }
    }
    return hiddenEmail + emailParts[1]
}

/**
 * input: 0775135700
 * output: ********5700
 */
fun getHiddenPhone(phone: String): String {
    var hiddenPhone = ""
    for (i in 0 until phone.length) {
        when (i) {
            phone.length - 1, phone.length - 2, phone.length - 3, phone.length - 4 -> hiddenPhone += phone[i]
            else -> hiddenPhone += "*"
        }
    }
    return hiddenPhone
}