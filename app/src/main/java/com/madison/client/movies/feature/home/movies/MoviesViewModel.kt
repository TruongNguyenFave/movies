package com.madison.client.movies.feature.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madison.client.movies.data.model.Category
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.data.repository.MoviesRepository
import com.madison.client.movies.data.repository.remote.api.error.RetrofitException
import com.madison.client.movies.feature.base.BaseViewModel
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private var moviesRepository: MoviesRepository) :
    BaseViewModel() {
    val loading = MutableLiveData<Boolean>()
    var movies = MutableLiveData<List<Movie>>()
    var error = MutableLiveData<RetrofitException>()

    var currentMovies = mutableListOf<Movie>()

    private var isQueryExhausted: Boolean = false
    private var isPerformingQuery: Boolean = false

    var sortBy: String? = Category.RELEASE_DATE.category

    private val _pageNumber = MutableLiveData<Int>()
    val pageNumber: LiveData<Int>
        get() = _pageNumber

    init {
        _pageNumber.value = 1
    }

    //for first page
    fun getList() {
        if (!isPerformingQuery) {
            isQueryExhausted = false
            isPerformingQuery = true
            getMovies()
        }
    }

    //for next page
    fun getNextPage() {
        if (!isQueryExhausted && !isPerformingQuery) {
            _pageNumber.value = _pageNumber.value?.plus(1)
            getMovies()
        }
    }

    fun getMovies() {
        compositeDisposable.add(moviesRepository.getMovies(pageNumber.value!!, sortBy)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe {
                loading.value = true
            }.doFinally {
                loading.value = false
            }.subscribe({
                currentMovies.addAll(it.results ?: listOf())
                movies.value = currentMovies
                isPerformingQuery = false
                isQueryExhausted = it.results?.size ?: 0 < 10
            }, {
                if (it is RetrofitException) error.value = it
            })
        )
    }

    fun resetPageNumber() {
        _pageNumber.value = 1
    }
}