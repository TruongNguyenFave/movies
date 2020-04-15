package com.madison.client.appname.feature.checkinresult

import com.madison.client.appname.di.scope.FragmentScope
import com.madison.client.appname.feature.checkinresult.checkinfailed.CheckInFailedFragment
import com.madison.client.appname.feature.checkinresult.checkinfailed.CheckInFailedModule
import com.madison.client.appname.feature.checkinresult.checkinsuccess.CheckInSuccessFragment
import com.madison.client.appname.feature.checkinresult.checkinsuccess.CheckInSuccessModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CheckInResultModule {
    @ContributesAndroidInjector(modules = [CheckInSuccessModule::class])
    @FragmentScope
    abstract fun bindCheckInSuccessFragment(): CheckInSuccessFragment

    @ContributesAndroidInjector(modules = [CheckInFailedModule::class])
    @FragmentScope
    abstract fun bindCheckInFailedFragment(): CheckInFailedFragment
}