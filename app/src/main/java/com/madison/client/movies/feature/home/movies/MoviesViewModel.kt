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
    companion object {
        private const val PER_PAGE = 20
    }

    val isLoading = MutableLiveData<Boolean>()
    var movies = MutableLiveData<List<Movie>>()
    var loadingError = MutableLiveData<RetrofitException>()

    private var isQueryExhausted: Boolean = false
    private var isExecutingQuery: Boolean = false

    var sortBy: String? = Category.RELEASE_DATE.category

    private val _pageNumber = MutableLiveData<Int>()
    val pageNumber: LiveData<Int>
        get() = _pageNumber

    init {
        _pageNumber.value = 1
    }

    //for first page
    fun getList() {
        if (!isExecutingQuery) {
            isQueryExhausted = false
            isExecutingQuery = true
            getListMovies()
        }
    }

    //for next page
    fun getNextPage() {
        if (!isQueryExhausted && !isExecutingQuery) {
            _pageNumber.value = _pageNumber.value?.plus(1)
            getListMovies()
        }
    }

    fun getListMovies() {
        compositeDisposable.add(moviesRepository.getMovies(pageNumber.value!!, sortBy)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe {
                isLoading.value = true
            }.doFinally {
                isLoading.value = false
            }.subscribe({
                movies.value = it.results
                isExecutingQuery = false
                isQueryExhausted = it.results?.size ?: 0 < PER_PAGE
            }, {
                if (it is RetrofitException) loadingError.value = it
            })
        )
    }

    fun resetPageNumber() {
        _pageNumber.value = 1
    }
}