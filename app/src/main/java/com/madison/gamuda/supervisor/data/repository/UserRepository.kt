package com.madison.gamuda.supervisor.data.repository

import com.madison.gamuda.supervisor.data.model.User
import com.madison.gamuda.supervisor.data.model.Worker
import com.madison.gamuda.supervisor.data.repository.local.UserLocalDataSource
import com.madison.gamuda.supervisor.data.repository.remote.UserRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class UserRepository constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) {
    fun signIn(userName: String, password: String): Single<User> {
        return userRemoteDataSource.signIn(userName, password)
    }

    fun checkIn(qrCode: String): Single<Worker> {
        return userRemoteDataSource.checkIn(qrCode)
    }

    fun logout(): Completable {
        return userRemoteDataSource.logout()
    }
}