package com.madison.gamuda.supervisor.data.repository.remote.api.middleware

import okhttp3.Interceptor
import okhttp3.Response

class NoneAuthInterceptor : ApiInterceptor() {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequestBuilder = originalRequest.newBuilder()
        return chain.proceed(newRequestBuilder.build())
    }
}
