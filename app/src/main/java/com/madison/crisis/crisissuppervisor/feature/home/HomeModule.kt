package com.madison.crisis.crisissuppervisor.feature.home

import com.madison.crisis.crisissuppervisor.di.scope.FragmentScope
import com.madison.crisis.crisissuppervisor.feature.home.scanworker.ScanWorkerFragment
import com.madison.crisis.crisissuppervisor.feature.home.scanworker.ScanWorkerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {
    @ContributesAndroidInjector(modules = [ScanWorkerModule::class])
    @FragmentScope
    abstract fun bindScanWorkerFragment(): ScanWorkerFragment
}