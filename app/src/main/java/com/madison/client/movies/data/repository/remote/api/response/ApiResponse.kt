package com.madison.client.movies.data.repository.remote.api.response

import com.google.gson.annotations.Expose

class ApiResponse<T>(
    @Expose var ok: Boolean?, @Expose var data: T
)