package com.madison.client.movies.feature.home.movies

import androidx.lifecycle.MutableLiveData
import com.madison.client.movies.data.model.MovieResponse
import com.madison.client.movies.data.repository.MoviesRepository
import com.madison.client.movies.data.repository.remote.api.error.RetrofitException
import com.madison.client.movies.feature.base.BaseViewModel
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private var moviesRepository: MoviesRepository) :
    BaseViewModel() {

    val isGettingMoviesObservable = MutableLiveData<Boolean>()
    var getMoviesSuccessObservable = MutableLiveData<MovieResponse>()
    var getMoviesErrorObservable = MutableLiveData<RetrofitException>()

    fun getMovies(page: Int) {
        compositeDisposable.add(moviesRepository.getMovies(page)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe {
                isGettingMoviesObservable.value = true
            }.doFinally {
                isGettingMoviesObservable.value = false
            }.subscribe({
                getMoviesSuccessObservable.value = it
            }, {
                if (it is RetrofitException) getMoviesErrorObservable.value = it
            })
        )
    }
}