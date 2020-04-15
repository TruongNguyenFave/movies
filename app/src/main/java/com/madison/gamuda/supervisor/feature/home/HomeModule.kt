package com.madison.gamuda.supervisor.feature.home

import com.madison.gamuda.supervisor.di.scope.FragmentScope
import com.madison.gamuda.supervisor.feature.home.scanworker.ScanWorkerFragment
import com.madison.gamuda.supervisor.feature.home.scanworker.ScanWorkerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {
    @ContributesAndroidInjector(modules = [ScanWorkerModule::class])
    @FragmentScope
    abstract fun bindScanWorkerFragment(): ScanWorkerFragment
}