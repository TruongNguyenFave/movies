package com.madison.gamuda.supervisor.di

import com.madison.gamuda.supervisor.di.scope.ActivityScope
import com.madison.gamuda.supervisor.feature.auth.AuthActivity
import com.madison.gamuda.supervisor.feature.auth.AuthModule
import com.madison.gamuda.supervisor.feature.checkinresult.CheckInResultActivity
import com.madison.gamuda.supervisor.feature.checkinresult.CheckInResultModule
import com.madison.gamuda.supervisor.feature.home.HomeActivity
import com.madison.gamuda.supervisor.feature.home.HomeModule
import com.madison.gamuda.supervisor.feature.qrcodescanner.QRCodeScannerActivity
import com.madison.gamuda.supervisor.feature.qrcodescanner.QRCodeScannerModule
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