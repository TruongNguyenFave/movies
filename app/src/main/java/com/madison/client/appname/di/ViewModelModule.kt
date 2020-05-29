package com.madison.client.appname.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madison.client.appname.feature.auth.signin.SignInViewModel
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
}