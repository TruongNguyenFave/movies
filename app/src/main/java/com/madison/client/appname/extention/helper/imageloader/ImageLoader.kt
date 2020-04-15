package com.madison.client.appname.extention.helper.imageloader

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.madison.client.appname.extention.helper.svgsupporter.GlideApp
import java.io.ByteArrayOutputStream

@SuppressLint("CheckResult")
private fun loadImage(
    imageView: ImageView,
    url: String?,
    uri: Uri?,
    placeHolder: Int = -1,
    isCircle: Boolean = false,
    radius: Int = 0
) {
    val requestManager = GlideApp.with(imageView.context)
    var requestBuilder: RequestBuilder<*>
    var requestOptions = RequestOptions()
    requestBuilder = when {
        uri != null -> requestManager.load(uri)
        url != null -> requestManager.load(url)
        else -> requestManager.load("")
    }
    if (placeHolder != -1) {
        requestOptions = requestOptions.placeholder(placeHolder)
    }
    if (isCircle) {
        requestBuilder.apply(RequestOptions.circleCropTransform())
    }
    if (radius > 0) requestBuilder.apply(
        RequestOptions().transforms(
            CenterCrop(), RoundedCorners(radius)
        )
    )

    requestBuilder = requestBuilder.apply(requestOptions)
    requestBuilder.into(imageView)
}

fun loadImage(
    imageView: ImageView,
    url: String?,
    placeHolder: Int = -1,
    isCircle: Boolean = false,
    radius: Int = 0
) {
    loadImage(imageView, url, null, placeHolder, isCircle, radius)
}

fun loadImage(
    imageView: ImageView,
    uri: Uri?,
    placeHolder: Int = -1,
    isCircle: Boolean = false,
    radius: Int = 0
) {
    loadImage(imageView, null, uri, placeHolder, isCircle, radius)
}

fun bitmapToByte(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}

fun loadBitmap(imageView: ImageView, bitmap: Bitmap) {
    Glide.with(imageView.context).load(bitmap).centerInside().into(imageView)
}

