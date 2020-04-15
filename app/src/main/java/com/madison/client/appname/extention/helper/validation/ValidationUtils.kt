package com.madison.client.appname.extention.helper.validation

import android.util.Patterns
import com.madison.client.appname.extention.helper.utils.EXCLUDE_SPECIAL_SYMBOL_REGEX
import com.madison.client.appname.extention.helper.utils.PHONE_REGEX

fun isValidPhoneNumber(target: String): Boolean {
    return target.matches(Regex(PHONE_REGEX))
}

fun isValidEmail(target: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

fun isValidUserName(target: String): Boolean {
    return target.matches(Regex(EXCLUDE_SPECIAL_SYMBOL_REGEX))
}