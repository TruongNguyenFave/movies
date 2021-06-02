package com.madison.client.appname.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.madison.client.appname.data.repository.remote.api.AuthApi
import com.madison.client.appname.data.repository.remote.api.middleware.AuthInterceptor
import com.madison.client.appname.data.repository.remote.api.service.ServiceGenerator
import com.madison.client.movies.BuildConfig
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
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }
}