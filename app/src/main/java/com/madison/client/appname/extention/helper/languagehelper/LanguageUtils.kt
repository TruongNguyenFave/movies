package com.madison.client.appname.extention.helper.languagehelper

import android.content.Context
import android.content.res.Configuration
import java.util.*

const val CODE_COUNTRY_MALAY = "MY"
const val NAME_COUNTRY_MALAY = "Malaysia"
const val DIAL_CODE_COUNTRY_MALAY = "+60"
const val LANGUAGE_VN = "vi"
const val LANGUAGE_EL = "en"
fun setLanguage(language: String, context: Context) {
    val locate = Locale(language)
    Locale.setDefault(locate)
    val config = Configuration()
    config.locale = locate
    context.resources.updateConfiguration(
        config,
        context.resources.displayMetrics
    )
}