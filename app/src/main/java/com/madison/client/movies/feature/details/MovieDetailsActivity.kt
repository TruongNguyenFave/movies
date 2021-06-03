package com.royal.pahang.durian.feature.record

import android.os.Bundle
import com.madison.client.movies.R
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.feature.base.BaseActivity
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment.Companion.MOVIE_EXTRA_KEY

class MovieDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.extras?.getParcelable<Movie>(MOVIE_EXTRA_KEY)

        navigator.addFragment(
            supportFragmentManager,
            MovieDetailsFragment.newInstance(movie),
            R.id.movie_detail_container,
            true
        )
    }
}