package com.madison.client.appname.data.repository.local.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madison.client.appname.data.model.DataBaseEntity

import com.madison.client.appname.data.repository.local.api.DatabaseManager.Companion.DATABASE_VERSION

@Database(entities = [DataBaseEntity::class], version = DATABASE_VERSION, exportSchema = true)
abstract class DatabaseManager : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "BaseProjectDatabase"
        const val DATABASE_VERSION = 1
    }
}
