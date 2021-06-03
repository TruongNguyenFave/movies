package com.madison.client.movies.data.repository

import com.madison.client.movies.data.model.MovieResponse
import com.madison.client.movies.data.repository.remote.MoviesRemoteDataSource
import io.reactivex.Single

class MoviesRepository constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {
    fun getMovies(page: Int, sortBy: String? = null): Single<MovieResponse> {
        return moviesRemoteDataSource.getMovies(page, sortBy)
    }
}