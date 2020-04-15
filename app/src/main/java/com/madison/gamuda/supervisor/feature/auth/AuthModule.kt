package com.madison.gamuda.supervisor.feature.auth

import com.madison.gamuda.supervisor.di.scope.FragmentScope
import com.madison.gamuda.supervisor.feature.auth.signin.SignInFragment
import com.madison.gamuda.supervisor.feature.auth.signin.SignInModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthModule {
    @ContributesAndroidInjector(modules = [SignInModule::class])
    @FragmentScope
    abstract fun bindSignInFragment(): SignInFragment
}