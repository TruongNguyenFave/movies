package com.madison.client.movies.di

import com.madison.client.movies.di.scope.ActivityScope
import com.madison.client.movies.feature.home.HomeActivity
import com.madison.client.movies.feature.home.HomeModule
import com.madison.client.movies.feature.details.MovieDetailsActivity
import com.madison.client.movies.feature.details.MovieDetailsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The [Module] class declares how to inject an Activity instance into corresponding
 * {@link Module}
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [HomeModule::class])
    @ActivityScope
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [MovieDetailsModule::class])
    @ActivityScope
    abstract fun bindMovieDetailsActivity(): MovieDetailsActivity
}