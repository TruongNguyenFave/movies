package com.madison.client.appname.feature.auth

import com.madison.client.appname.di.scope.FragmentScope
import com.madison.client.appname.feature.auth.signin.SignInFragment
import com.madison.client.appname.feature.auth.signin.SignInModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthModule {
    @ContributesAndroidInjector(modules = [SignInModule::class])
    @FragmentScope
    abstract fun bindSignInFragment(): SignInFragment
}