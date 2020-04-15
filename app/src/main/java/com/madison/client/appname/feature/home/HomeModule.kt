package com.madison.client.appname.feature.home

import com.madison.client.appname.di.scope.FragmentScope
import com.madison.client.appname.feature.home.scanworker.ScanWorkerFragment
import com.madison.client.appname.feature.home.scanworker.ScanWorkerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {
    @ContributesAndroidInjector(modules = [ScanWorkerModule::class])
    @FragmentScope
    abstract fun bindScanWorkerFragment(): ScanWorkerFragment
}