package com.madison.crisis.crisissuppervisor.di

import com.madison.crisis.crisissuppervisor.di.scope.ActivityScope
import com.madison.crisis.crisissuppervisor.feature.auth.AuthActivity
import com.madison.crisis.crisissuppervisor.feature.auth.AuthModule
import com.madison.crisis.crisissuppervisor.feature.checkinresult.CheckInResultActivity
import com.madison.crisis.crisissuppervisor.feature.checkinresult.CheckInResultModule
import com.madison.crisis.crisissuppervisor.feature.home.HomeActivity
import com.madison.crisis.crisissuppervisor.feature.home.HomeModule
import com.madison.crisis.crisissuppervisor.feature.qrcodescanner.QRCodeScannerActivity
import com.madison.crisis.crisissuppervisor.feature.qrcodescanner.QRCodeScannerModule
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