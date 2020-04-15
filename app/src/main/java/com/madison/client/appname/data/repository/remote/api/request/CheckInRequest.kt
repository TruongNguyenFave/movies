package com.madison.client.appname.data.repository.remote.api.request

import com.google.gson.annotations.Expose

class CheckInRequest(
    @Expose val qrCode: String
)