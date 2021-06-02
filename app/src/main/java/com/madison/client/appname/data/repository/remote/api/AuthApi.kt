package com.madison.client.appname.data.repository.remote.api

import com.madison.client.appname.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AuthApi {
    @GET("/api/app/collection-center?view=all")
    fun getMovies(): Single<MovieResponse>
}