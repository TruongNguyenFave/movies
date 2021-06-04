package com.madison.client.movies.feature.details

import android.os.Bundle
import android.view.MenuItem
import com.madison.client.movies.R
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.feature.base.BaseActivity
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment.Companion.MOVIE_EXTRA_KEY
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(toolbarDetail)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val movie = intent.extras?.getParcelable<Movie>(MOVIE_EXTRA_KEY)

        navigator.addFragment(
            supportFragmentManager,
            MovieDetailsFragment.newInstance(movie),
            R.id.movie_detail_container,
            true
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}