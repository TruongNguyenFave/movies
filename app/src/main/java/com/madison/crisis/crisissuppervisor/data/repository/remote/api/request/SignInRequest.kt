package com.madison.crisis.crisissuppervisor.data.repository.remote.api.request

import com.google.gson.annotations.Expose

class SignInRequest(
    @Expose val username: String, @Expose val password: String
)