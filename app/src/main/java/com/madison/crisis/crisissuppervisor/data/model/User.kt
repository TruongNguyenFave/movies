package com.madison.crisis.crisissuppervisor.data.model

import com.google.gson.annotations.Expose

data class User(
    @Expose val id: String?, @Expose val name: String?, @Expose val username: String?, @Expose val password: String?
)