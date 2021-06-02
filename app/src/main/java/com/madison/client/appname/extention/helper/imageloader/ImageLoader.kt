package com.madison.client.appname.extention.helper.imageloader

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide


fun loadBitmap(imageView: ImageView, bitmap: Bitmap) {
    Glide.with(imageView.context).load(bitmap).centerInside().into(imageView)
}

