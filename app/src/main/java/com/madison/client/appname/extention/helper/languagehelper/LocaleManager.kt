package com.madison.client.appname.extention.helper.languagehelper

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import androidx.preference.PreferenceManager
import java.util.*

class LocaleManager {
    companion object {
        const val EN_LANGUAGE = "en"

        private const val PREFS_LANGUAGE = "PREFS_LANGUAGE"

        fun setLocale(context: Context): Context {
            val savedLanguage = getSavedLanguage(context)
            return updateResources(context, savedLanguage)
        }

        fun getSavedLanguage(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(
                PREFS_LANGUAGE, EN_LANGUAGE
            ) ?: EN_LANGUAGE
        }

        fun isEnLanguage(context: Context): Boolean {
            return getSavedLanguage(context) == EN_LANGUAGE
        }

        fun getCurrentLocale(context: Context): Locale {
            return when (getSavedLanguage(context)) {
                EN_LANGUAGE -> Locale.ENGLISH
                else -> Locale.ENGLISH
            }
        }

        fun setNewLocale(context: Context, language: String): Context {
            persistLanguage(context, language)
            return updateResources(context, language)
        }

        fun getDeviceLanguage(res: Resources): String {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0).language.toString() else config.locale.language.toString()
        }

        fun getDeviceLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
        }

        private fun persistLanguage(context: Context, language: String) {
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREFS_LANGUAGE, language).apply()
        }

        private fun updateResources(context: Context, language: String): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)
            return updateResources(context, locale)
        }

        private fun updateResources(context: Context, locale: Locale): Context {
            val res = context.resources
            val config = Configuration(res.configuration)

            if (Build.VERSION.SDK_INT >= 26) {
                config.setLocale(locale)
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                config.setLocales(localeList)
            } else {
                config.locale = locale
                res.updateConfiguration(config, res.displayMetrics)
            }
            return context.createConfigurationContext(config)
        }
    }
}