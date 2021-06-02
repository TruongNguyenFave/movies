package com.madison.client.appname.data.repository.remote.api.middleware

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : ApiInterceptor() {

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