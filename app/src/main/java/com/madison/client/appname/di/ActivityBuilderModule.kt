package com.madison.client.appname.di

import com.madison.client.appname.di.scope.ActivityScope
import com.madison.client.appname.feature.auth.AuthActivity
import com.madison.client.appname.feature.auth.AuthModule
import com.madison.client.appname.feature.checkinresult.CheckInResultActivity
import com.madison.client.appname.feature.checkinresult.CheckInResultModule
import com.madison.client.appname.feature.home.HomeActivity
import com.madison.client.appname.feature.home.HomeModule
import com.madison.client.appname.feature.qrcodescanner.QRCodeScannerActivity
import com.madison.client.appname.feature.qrcodescanner.QRCodeScannerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The [Module] class declares how to inject an Activity instance into corresponding
 * {@link Module}
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [AuthModule::class])
    @ActivityScope
    abstract fun bindAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    @ActivityScope
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [CheckInResultModule::class])
    @ActivityScope
    abstract fun bindCheckInResultActivity(): CheckInResultActivity

    @ContributesAndroidInjector(modules = [QRCodeScannerModule::class])
    @ActivityScope
    abstract fun bindQRCodeScannerActivity(): QRCodeScannerActivity
}