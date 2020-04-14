package com.madison.crisis.crisissuppervisor.feature.auth

import com.madison.crisis.crisissuppervisor.di.scope.FragmentScope
import com.madison.crisis.crisissuppervisor.feature.auth.signin.SignInFragment
import com.madison.crisis.crisissuppervisor.feature.auth.signin.SignInModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthModule {
    @ContributesAndroidInjector(modules = [SignInModule::class])
    @FragmentScope
    abstract fun bindSignInFragment(): SignInFragment
}