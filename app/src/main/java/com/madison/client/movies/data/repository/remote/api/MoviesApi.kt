package com.madison.client.movies.data.repository.remote.api

import com.madison.client.movies.data.model.MovieResponse
import com.madison.client.movies.extention.helper.utils.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("discover/movie")
    fun getMovies(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String?,
        @Query("api_key") apiKey: String = API_KEY
    ): Single<MovieResponse>
}