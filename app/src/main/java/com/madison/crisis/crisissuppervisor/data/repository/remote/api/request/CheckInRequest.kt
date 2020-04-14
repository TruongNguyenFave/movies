package com.madison.crisis.crisissuppervisor.data.repository.remote.api.request

import com.google.gson.annotations.Expose

class CheckInRequest(
    @Expose val qrCode: String
)