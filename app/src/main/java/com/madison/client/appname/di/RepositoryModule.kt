package com.madison.client.appname.di

import android.content.Context
import androidx.room.Room
import com.madison.client.appname.data.repository.MoviesRepository
import com.madison.client.appname.data.repository.local.api.*
import com.madison.client.appname.data.repository.remote.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideSharedPrefApi(context: Context): SharedPrefApi {
        return SharedPrefApi(context)
    }

    @Singleton
    @Provides
    fun provideMoviesRepository(
            moviesRemoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository {
        return MoviesRepository(moviesRemoteDataSource)
    }
}