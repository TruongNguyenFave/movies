package com.madison.client.movies.extention.helper.viewextension

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.extention.helper.utils.IMAGE_BASE_URL
import com.madison.client.movies.feature.home.movies.adapter.MovieAdapter

fun View.safeClick(listener: View.OnClickListener, blockInMillis: Long = 500) {
    var lastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < blockInMillis) return@setOnClickListener
        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClick(this)
    }
}

fun View.handleGoneVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("movieImage")
fun ImageView.loadImage(imageUrl: String?) {
    val fullImageUrl = IMAGE_BASE_URL + imageUrl
    Glide.with(this.context)
        .load(fullImageUrl)
        .placeholder(com.madison.client.movies.R.drawable.image_placeholder)
        .into(this)
}

@BindingAdapter("movieList")
fun RecyclerView.submitMovieList(movies: List<Movie>?) {
    val adapter = this.adapter as MovieAdapter
    movies?.let {
        adapter.submitList(movies)
    }
}

