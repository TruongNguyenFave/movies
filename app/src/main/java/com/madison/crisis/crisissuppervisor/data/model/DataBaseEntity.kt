package com.madison.crisis.crisissuppervisor.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataBaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)