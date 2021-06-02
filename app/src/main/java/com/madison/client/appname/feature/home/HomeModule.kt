package com.madison.client.appname.feature.home

import com.madison.client.appname.di.scope.FragmentScope
import com.royal.pahang.durian.feature.fruitsupply.fruitsupply.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {
    @ContributesAndroidInjector
    @FragmentScope
    abstract fun bindMoviesFragment(): MoviesFragment
}