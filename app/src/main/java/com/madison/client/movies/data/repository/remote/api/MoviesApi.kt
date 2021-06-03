package com.madison.client.movies.data.repository.remote.api

import com.madison.client.movies.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("discover/movie")
    fun getMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "328c283cd27bd1877d9080ccb1604c91"
    ): Single<MovieResponse>
}