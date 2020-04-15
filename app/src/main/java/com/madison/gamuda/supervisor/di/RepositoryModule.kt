package com.madison.gamuda.supervisor.di

import android.content.Context
import androidx.room.Room
import com.madison.gamuda.supervisor.data.repository.UserRepository
import com.madison.gamuda.supervisor.data.repository.local.UserLocalDataSource
import com.madison.gamuda.supervisor.data.repository.local.api.*
import com.madison.gamuda.supervisor.data.repository.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAccessTokenWrapper(sharedPrefApi: SharedPrefApi): AccessTokenWrapper {
        return AccessTokenWrapper(sharedPrefApi)
    }

    @Singleton
    @Provides
    fun provideSharedPrefApi(context: Context): SharedPrefApi {
        return SharedPrefApi(context)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource, userRemoteDataSource: UserRemoteDataSource
    ): UserRepository {
        return UserRepository(userLocalDataSource, userRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideDatabaseManager(context: Context): DatabaseManager {
        return Room.databaseBuilder(
            context, DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDatabaseApi(databaseManager: DatabaseManager): DatabaseApi {
        return DatabaseApiImpl(databaseManager)
    }
}