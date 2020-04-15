package com.madison.client.appname.data.repository.local

import com.madison.client.appname.data.repository.local.api.DatabaseApi
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val databaseApi: DatabaseApi) :
    BaseLocalDataSource()