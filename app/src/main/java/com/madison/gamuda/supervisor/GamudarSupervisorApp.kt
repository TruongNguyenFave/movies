package com.madison.gamuda.supervisor

import com.madison.gamuda.supervisor.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GamudarSupervisorApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}