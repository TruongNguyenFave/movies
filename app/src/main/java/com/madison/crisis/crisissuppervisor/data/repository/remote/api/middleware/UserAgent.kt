package com.madison.crisis.crisissuppervisor.data.repository.remote.api.middleware

import com.karumi.dexter.BuildConfig


class UserAgent {
    fun getAppVersion(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getAppId(): String {
        return ""
    }
}