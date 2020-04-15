package com.madison.client.appname.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Worker(
    @Expose val id: String?, @Expose val name: String?, @Expose val phone: String?, @Expose val idNo: String?, @Expose val address: String?, @Expose val userCode: String?, @Expose val qrCodeUrl: String?, @Expose val firstCheckin: String?, @Expose val testResultDate: String?, @Expose val resultUrl: String?, @Expose val status: String?, @Expose val createdAt: String?, @Expose val updatedAt: String?, @Expose @SerializedName(
        "LocationId"
    ) val locationId: String?, @Expose @SerializedName("ProjectId") val projectId: String?, @Expose @SerializedName(
        "Project"
    ) val project: Project?
) : Parcelable