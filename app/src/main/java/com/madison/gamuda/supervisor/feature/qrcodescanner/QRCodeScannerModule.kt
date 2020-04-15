package com.madison.gamuda.supervisor.feature.qrcodescanner

import com.madison.gamuda.supervisor.feature.qrcodescanner.qrcodescannerview.QRCodeScannerFragment
import com.madison.gamuda.supervisor.feature.qrcodescanner.qrcodescannerview.QRCodeScannerViewModule
import com.madison.gamuda.supervisor.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QRCodeScannerModule {
    @ContributesAndroidInjector(modules = [QRCodeScannerViewModule::class])
    @FragmentScope
    abstract fun bindQRCodeScannerFragment(): QRCodeScannerFragment
}