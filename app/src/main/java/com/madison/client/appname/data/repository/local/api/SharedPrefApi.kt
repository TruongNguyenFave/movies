package com.madison.client.appname.data.repository.local.api

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPrefApi(context: Context) {
    private var gson: Gson

    private val sharedPreferences: SharedPreferences

    init {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        gson = Gson()
    }

    fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
            else -> editor.putString(key, gson.toJson(data))
        }
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, clazz: Class<T>): T? {
        if (!sharedPreferences.contains(key)) {
            return null
        }
        return when (clazz) {
            String::class.java -> sharedPreferences.getString(key, "") as T
            Boolean::class.java -> java.lang.Boolean.valueOf(
                sharedPreferences.getBoolean(key, false)
            ) as T
            Float::class.java -> java.lang.Float.valueOf(sharedPreferences.getFloat(key, 0f)) as T
            Int::class.java -> Integer.valueOf(sharedPreferences.getInt(key, 0)) as T
            Long::class.java -> java.lang.Long.valueOf(sharedPreferences.getLong(key, 0)) as T
            else -> {
                gson.fromJson(sharedPreferences.getString(key, ""), clazz)
            }
        }
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getStringSet(key: String): Set<String>? {
        return sharedPreferences.getStringSet(key, HashSet())
    }

    fun putStringSet(key: String, cookies: HashSet<String>) {
        sharedPreferences.edit().putStringSet(key, cookies).apply()
    }


    fun removeKey(key: String) {
        sharedPreferences.edit().let {
            it.remove(key)
            it.apply()
        }
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }

    companion object {
        const val PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN"

        private const val PREFS_NAME = "Preferences"
    }
}