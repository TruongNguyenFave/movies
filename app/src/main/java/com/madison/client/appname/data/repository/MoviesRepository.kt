package com.madison.client.appname.data.repository

import com.madison.client.appname.data.model.MovieResponse
import com.madison.client.appname.data.repository.remote.MoviesRemoteDataSource
import io.reactivex.Single

class MoviesRepository constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {
    fun getMovies(page: Int): Single<MovieResponse> {
        return moviesRemoteDataSource.getMovies(page)
    }
}