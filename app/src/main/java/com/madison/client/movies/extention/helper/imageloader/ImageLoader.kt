package com.madison.client.movies.extention.helper.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.madison.client.movies.R

fun loadImage(context: Context, imageView: ImageView, url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.image_placeholder)
        .into(imageView)
}

