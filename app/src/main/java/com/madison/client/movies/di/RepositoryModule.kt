package com.madison.client.movies.di

import android.content.Context
import com.madison.client.movies.data.repository.MoviesRepository
import com.madison.client.movies.data.repository.local.api.SharedPrefApi
import com.madison.client.movies.data.repository.remote.MoviesRemoteDataSource
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