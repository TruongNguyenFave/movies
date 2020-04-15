package com.madison.gamuda.supervisor.data.repository.remote

import com.madison.gamuda.supervisor.data.model.User
import com.madison.gamuda.supervisor.data.model.Worker
import com.madison.gamuda.supervisor.data.repository.local.api.AccessTokenWrapper
import com.madison.gamuda.supervisor.data.repository.remote.api.AuthApi
import com.madison.gamuda.supervisor.data.repository.remote.api.NoneAuthApi
import com.madison.gamuda.supervisor.data.repository.remote.api.request.CheckInRequest
import com.madison.gamuda.supervisor.data.repository.remote.api.request.SignInRequest
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val authApi: AuthApi,
    private val noneAuthApi: NoneAuthApi,
    private val accessTokenWrapper: AccessTokenWrapper
) : BaseRemoteDataSource() {
    fun signIn(userName: String, password: String): Single<User> {
        return authApi.login(SignInRequest(userName, password)).map { it.data }
    }

    fun checkIn(qrCode: String): Single<Worker> {
        return authApi.checkIn(CheckInRequest(qrCode)).map { it.data }
    }

    fun logout(): Completable {
        return authApi.logout().flatMapCompletable {
            accessTokenWrapper.clearData()
            Completable.complete()
        }
    }
}