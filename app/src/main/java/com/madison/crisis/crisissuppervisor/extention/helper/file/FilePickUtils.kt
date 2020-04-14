package com.madison.crisis.crisissuppervisor.extention.helper.file

import android.content.Context
import android.net.Uri
import androidx.annotation.NonNull
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object FilePickUtils {

    @NonNull
    fun createPartFromString(param: String): RequestBody? {
        return RequestBody.create(MultipartBody.FORM, param)
    }

    @NonNull
    fun prepareFilePart(
        fileUri: Uri?,
        context: Context,
        fileName: String
    ): MultipartBody.Part? {
        if (fileUri == null) {
            return null
        }
        val file = getCacheImagePath(context, fileName, fileUri)

        val requestFile = RequestBody.create(
            MediaType.parse(context.contentResolver.getType(fileUri) ?: ""),
            file
        )
        return MultipartBody.Part.createFormData(
            "avatar",
            System.currentTimeMillis().toString(),
            requestFile
        )
    }

    private fun getCacheImagePath(context: Context, fileName: String, fileUri: Uri): File {
        val path = File(context.externalCacheDir, "camera")
        if (!path.exists()) path.mkdirs()
        return if (fileName.isEmpty()) {
            FileUtils.getFile(context, fileUri)
        } else
            File(path, fileName)
    }
}