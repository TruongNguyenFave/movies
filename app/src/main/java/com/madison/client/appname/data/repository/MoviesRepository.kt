package com.madison.client.appname.data.repository

import com.madison.client.appname.data.repository.remote.MoviesRemoteDataSource

class MoviesRepository constructor(
        private val userRemoteDataSource: MoviesRemoteDataSource
)