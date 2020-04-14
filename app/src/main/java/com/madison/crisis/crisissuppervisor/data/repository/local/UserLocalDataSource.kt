package com.madison.crisis.crisissuppervisor.data.repository.local

import com.madison.crisis.crisissuppervisor.data.repository.local.api.DatabaseApi
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val databaseApi: DatabaseApi) :
    BaseLocalDataSource()