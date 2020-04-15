package com.madison.gamuda.supervisor.data.repository.local.api

import javax.inject.Inject

class AccessTokenWrapper @Inject constructor(private val sharedPrefApi: SharedPrefApi) {
    private var accessToken: String? = null

    fun getAccessToken(): String? {
        // access token can be null in case user not login yet
        if (accessToken == null) {
            accessToken = sharedPrefApi.get(
                SharedPrefApi.PREF_ACCESS_TOKEN, String::class.java
            )
        }
        return accessToken
    }

    fun saveAccessToken(accessToken: String) {
        this.accessToken = accessToken
        sharedPrefApi.put(SharedPrefApi.PREF_ACCESS_TOKEN, accessToken)
    }

    fun clearData() {
        sharedPrefApi.removeKey(SharedPrefApi.PREF_ACCESS_TOKEN)
    }
}