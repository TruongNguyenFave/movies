package com.madison.gamuda.supervisor.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Project(
    @Expose val id: String?, @Expose val name: String?, @Expose val description: String?, @Expose val address: String?, @Expose val createdAt: String?, @Expose val updatedAt: String?, @Expose @SerializedName(
        "AdminId"
    ) val adminId: String?
) : Parcelable