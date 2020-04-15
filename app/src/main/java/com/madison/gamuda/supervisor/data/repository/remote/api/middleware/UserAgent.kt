package com.madison.gamuda.supervisor.data.repository.remote.api.middleware

import com.karumi.dexter.BuildConfig


class UserAgent {
    fun getAppVersion(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getAppId(): String {
        return ""
    }
}