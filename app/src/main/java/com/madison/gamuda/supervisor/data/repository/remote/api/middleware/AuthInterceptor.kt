package com.madison.gamuda.supervisor.data.repository.remote.api.middleware

import com.madison.gamuda.supervisor.data.repository.local.api.AccessTokenWrapper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val accessTokenWrapper: AccessTokenWrapper
) : ApiInterceptor() {

    companion object {
        private const val BEARER = "Bearer"
        private const val AUTHORIZATION_KEY = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val builder = original.newBuilder().method(original.method(), original.body())
        return chain.proceed(builder.build())
    }
}