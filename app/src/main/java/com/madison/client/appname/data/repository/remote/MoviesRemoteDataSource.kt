package com.madison.client.appname.data.repository.remote

import com.madison.client.appname.data.repository.remote.api.AuthApi
import com.madison.client.appname.data.repository.remote.api.NoneAuthApi
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
        private val authApi: AuthApi,
        private val noneAuthApi: NoneAuthApi
) : BaseRemoteDataSource() {

}