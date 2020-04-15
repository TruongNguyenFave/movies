package com.madison.client.appname.data.repository.remote.api.request

import com.google.gson.annotations.Expose

class SignInRequest(
    @Expose val username: String, @Expose val password: String
)