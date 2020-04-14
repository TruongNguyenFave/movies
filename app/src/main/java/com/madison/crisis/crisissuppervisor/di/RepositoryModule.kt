package com.madison.crisis.crisissuppervisor.di

import android.content.Context
import androidx.room.Room
import com.madison.crisis.crisissuppervisor.data.repository.UserRepository
import com.madison.crisis.crisissuppervisor.data.repository.local.UserLocalDataSource
import com.madison.crisis.crisissuppervisor.data.repository.local.api.*
import com.madison.crisis.crisissuppervisor.data.repository.remote.UserRemoteDataSource
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