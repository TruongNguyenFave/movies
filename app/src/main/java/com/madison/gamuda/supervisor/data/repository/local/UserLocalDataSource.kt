package com.madison.gamuda.supervisor.data.repository.local

import com.madison.gamuda.supervisor.data.repository.local.api.DatabaseApi
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val databaseApi: DatabaseApi) :
    BaseLocalDataSource()