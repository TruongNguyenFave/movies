package com.madison.gamuda.supervisor.data.repository.remote.api.request

import com.google.gson.annotations.Expose

class SignInRequest(
    @Expose val username: String, @Expose val password: String
)