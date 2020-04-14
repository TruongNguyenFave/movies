package com.madison.crisis.crisissuppervisor.data.repository.remote.api.response

import com.google.gson.annotations.Expose

data class ServerSuccessResponse(
    @Expose
    val message: String
)