package com.madison.gamuda.supervisor.feature.checkinresult

import com.madison.gamuda.supervisor.di.scope.FragmentScope
import com.madison.gamuda.supervisor.feature.checkinresult.checkinfailed.CheckInFailedFragment
import com.madison.gamuda.supervisor.feature.checkinresult.checkinfailed.CheckInFailedModule
import com.madison.gamuda.supervisor.feature.checkinresult.checkinsuccess.CheckInSuccessFragment
import com.madison.gamuda.supervisor.feature.checkinresult.checkinsuccess.CheckInSuccessModule
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