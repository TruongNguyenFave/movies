package com.madison.client.movies.data.repository.remote.api.response

import com.google.gson.annotations.Expose

data class ServerSuccessResponse(
    @Expose
    val message: String
)