package com.madison.client.appname.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.royal.pahang.durian.feature.fruitsupply.fruitsupply.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

}