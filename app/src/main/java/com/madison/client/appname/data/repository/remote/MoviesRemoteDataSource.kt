package com.madison.client.appname.data.repository.remote

import com.madison.client.appname.data.model.MovieResponse
import com.madison.client.appname.data.repository.remote.api.MoviesApi
import io.reactivex.Single
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
) : BaseRemoteDataSource() {
    fun getMovies(): Single<MovieResponse> {
        return moviesApi.getMovies()
    }
}