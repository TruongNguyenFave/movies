package com.madison.crisis.crisissuppervisor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madison.crisis.crisissuppervisor.feature.auth.signin.SignInViewModel
import com.madison.crisis.crisissuppervisor.feature.home.scanworker.ScanWorkerViewModel
import com.madison.crisis.crisissuppervisor.feature.qrcodescanner.qrcodescannerview.QRCodeScannerViewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    internal abstract fun bindSignUpViewModel(signUpViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QRCodeScannerViewViewModel::class)
    internal abstract fun bindQRCodeScannerViewViewModel(qRCodeScannerViewViewModel: QRCodeScannerViewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanWorkerViewModel::class)
    internal abstract fun bindScanWorkerViewModel(scanWorkerViewModel: ScanWorkerViewModel): ViewModel
}