package com.madison.client.appname.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataBaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)