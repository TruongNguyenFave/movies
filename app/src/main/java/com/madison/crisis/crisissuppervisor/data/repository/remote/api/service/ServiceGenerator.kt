package com.madison.crisis.crisissuppervisor.data.repository.remote.api.service

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.madison.crisis.crisissuppervisor.data.repository.remote.api.middleware.PersistentCookieStore
import com.madison.crisis.crisissuppervisor.data.repository.remote.api.middleware.RxErrorHandlingCallAdapterFactory
import com.madison.crisis.crisissuppervisor.data.repository.remote.api.middleware.UnsafeOkHttpClient.Companion.getUnsafeOkHttpClient
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {

        private const val CONNECTION_TIMEOUT = 15L

        fun <T> generate(
            baseUrl: String,
            serviceClass: Class<T>,
            gson: Gson,
            authenticator: Authenticator?,
            interceptors: Array<Interceptor>,
            context: Context
        ): T {
            val okHttpClientBuilder = getUnsafeOkHttpClient()
            val cookieManager =
                CookieManager(PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL)

            for (interceptor in interceptors) {
                okHttpClientBuilder.addInterceptor(interceptor)
            }
            okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okHttpClientBuilder.cookieJar(JavaNetCookieJar(cookieManager))

            if (authenticator != null) {
                okHttpClientBuilder.authenticator(authenticator)
            }
            val builder = Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
            val retrofit = builder.client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            return retrofit.create(serviceClass)
        }
    }
}