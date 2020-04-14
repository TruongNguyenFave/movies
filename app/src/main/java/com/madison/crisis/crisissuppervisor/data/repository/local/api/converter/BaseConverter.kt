package com.madison.crisis.crisissuppervisor.data.repository.local.api.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

open class BaseConverter<T> {
    private val gson = Gson()

    @TypeConverter
    fun fromString(data: String?): List<T> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<T>>() {}.type
        return gson.fromJson<List<T>>(data, listType)
    }

    @TypeConverter
    fun fromList(someObjects: List<T>?): String {
        if (someObjects == null) {
            return ""
        }
        return gson.toJson(someObjects)
    }
}
