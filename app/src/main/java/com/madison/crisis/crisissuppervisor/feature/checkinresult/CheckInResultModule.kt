package com.madison.crisis.crisissuppervisor.feature.checkinresult

import com.madison.crisis.crisissuppervisor.di.scope.FragmentScope
import com.madison.crisis.crisissuppervisor.feature.checkinresult.checkinfailed.CheckInFailedFragment
import com.madison.crisis.crisissuppervisor.feature.checkinresult.checkinfailed.CheckInFailedModule
import com.madison.crisis.crisissuppervisor.feature.checkinresult.checkinsuccess.CheckInSuccessFragment
import com.madison.crisis.crisissuppervisor.feature.checkinresult.checkinsuccess.CheckInSuccessModule
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