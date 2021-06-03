package com.madison.client.movies.feature.bookmovie

import com.madison.client.movies.di.scope.FragmentScope
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BookMovieModule {
    @ContributesAndroidInjector
    @FragmentScope
    abstract fun bindMovieDetailsFragment(): MovieDetailsFragment
}