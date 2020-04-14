package com.madison.crisis.crisissuppervisor.data.repository.remote.api

import com.madison.crisis.crisissuppervisor.data.model.User
import com.madison.crisis.crisissuppervisor.data.model.Worker
import com.madison.crisis.crisissuppervisor.data.repository.remote.api.request.CheckInRequest
import com.madison.crisis.crisissuppervisor.data.repository.remote.api.request.SignInRequest
import com.madison.crisis.crisissuppervisor.data.repository.remote.api.response.ApiResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/login")
    fun login(@Body signInRequest: SignInRequest): Single<ApiResponse<User>>

    @POST("/api/checkin")
    fun checkIn(@Body checkInRequest: CheckInRequest): Single<ApiResponse<Worker>>

    @POST("/api/logout")
    fun logout(): Single<String>
}