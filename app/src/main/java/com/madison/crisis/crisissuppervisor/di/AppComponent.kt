package com.madison.crisis.crisissuppervisor.di

import android.app.Application
import com.madison.crisis.crisissuppervisor.GamudarSupervisorApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilderModule::class, RepositoryModule::class, NetworkModule::class]
)
internal interface AppComponent : AndroidInjector<GamudarSupervisorApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}