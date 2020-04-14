package com.madison.crisis.crisissuppervisor.data.repository.remote.api.middleware

import com.madison.crisis.crisissuppervisor.data.repository.remote.api.middleware.ApiInterceptor
import okhttp3.Interceptor
import okhttp3.Response

class NoneAuthInterceptor : ApiInterceptor() {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequestBuilder = originalRequest.newBuilder()
        return chain.proceed(newRequestBuilder.build())
    }
}
