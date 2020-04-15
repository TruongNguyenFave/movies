package com.madison.client.appname.feature.qrcodescanner

import com.madison.client.appname.feature.qrcodescanner.qrcodescannerview.QRCodeScannerFragment
import com.madison.client.appname.feature.qrcodescanner.qrcodescannerview.QRCodeScannerViewModule
import com.madison.client.appname.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class QRCodeScannerModule {
    @ContributesAndroidInjector(modules = [QRCodeScannerViewModule::class])
    @FragmentScope
    abstract fun bindQRCodeScannerFragment(): QRCodeScannerFragment
}