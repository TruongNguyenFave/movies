package com.madison.crisis.crisissuppervisor.feature.qrcodescanner

import com.madison.crisis.crisissuppervisor.feature.qrcodescanner.qrcodescannerview.QRCodeScannerFragment
import com.madison.crisis.crisissuppervisor.feature.qrcodescanner.qrcodescannerview.QRCodeScannerViewModule
import com.madison.crisis.crisissuppervisor.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QRCodeScannerModule {
    @ContributesAndroidInjector(modules = [QRCodeScannerViewModule::class])
    @FragmentScope
    abstract fun bindQRCodeScannerFragment(): QRCodeScannerFragment
}