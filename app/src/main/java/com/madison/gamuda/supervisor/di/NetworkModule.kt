package com.madison.gamuda.supervisor.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.madison.gamuda.supervisor.BuildConfig
import com.madison.gamuda.supervisor.data.repository.local.api.AccessTokenWrapper
import com.madison.gamuda.supervisor.data.repository.remote.api.AuthApi
import com.madison.gamuda.supervisor.data.repository.remote.api.NoneAuthApi
import com.madison.gamuda.supervisor.data.repository.remote.api.middleware.AuthInterceptor
import com.madison.gamuda.supervisor.data.repository.remote.api.middleware.NoneAuthInterceptor
import com.madison.gamuda.supervisor.data.repository.remote.api.service.ServiceGenerator
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    @Singleton
    @Provides
    fun provideNoneAuthApi(
        gson: Gson,
        noneAuthInterceptor: NoneAuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        context: Context
    ): NoneAuthApi {
        val interceptors = arrayOf(noneAuthInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(
            BuildConfig.BASE_URL, NoneAuthApi::class.java, gson, null, interceptors, context
        )
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        gson: Gson,
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        context: Context
    ): AuthApi {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(
            BuildConfig.BASE_URL, AuthApi::class.java, gson, null, interceptors, context
        )
    }

    @Singleton
    @Provides
    fun provideNoneAuthInterceptor(
    ): NoneAuthInterceptor {
        return NoneAuthInterceptor()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(accessTokenWrapper: AccessTokenWrapper): AuthInterceptor {
        return AuthInterceptor(accessTokenWrapper)
    }
}