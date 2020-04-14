package com.madison.crisis.crisissuppervisor.data.repository.local.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madison.crisis.crisissuppervisor.data.model.DataBaseEntity

import com.madison.crisis.crisissuppervisor.data.repository.local.api.DatabaseManager.Companion.DATABASE_VERSION

@Database(entities = [DataBaseEntity::class], version = DATABASE_VERSION)
abstract class DatabaseManager : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "GamudaSupervisorDatabase"
        const val DATABASE_VERSION = 1
    }
}
