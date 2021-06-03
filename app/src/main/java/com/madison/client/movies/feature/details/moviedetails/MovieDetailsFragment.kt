package com.madison.client.movies.feature.details.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.madison.client.movies.R
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.extention.helper.imageloader.loadImage
import com.madison.client.movies.feature.base.BaseFragment
import com.royal.pahang.durian.feature.record.MovieDetailsActivity
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailsFragment : BaseFragment() {

    companion object {
        const val MOVIE_EXTRA_KEY = "MOVIE_EXTRA_KEY"

        fun newInstance(movie: Movie?): MovieDetailsFragment {
            val bundle = Bundle().apply {
                putParcelable(MOVIE_EXTRA_KEY, movie)
            }
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun initView() {
        arguments?.let {
            if (it.containsKey(MOVIE_EXTRA_KEY)) {
                it.getParcelable<Movie>(MOVIE_EXTRA_KEY)?.let {
                    setMovieDetailsData(it)
                }
            }
        }
    }

    private fun setMovieDetailsData(movie: Movie) {
        val imvMovieBackdrop =
            requireActivity().findViewById<ImageView>(R.id.imvMovieBackdrop)
        loadImage(requireContext(), imvMovieBackdrop, movie.getFullBackdropPath() ?: "")
        loadImage(requireContext(), imvMoviePoster, movie.getFullPosterPath() ?: "")
        tvMovieTitle.setText(movie.originalTitle)
        tvMovieOverview.setText(movie.overview)
        tvMovieReleaseDate.setText(getString(R.string.movie_release_date, movie.releaseDate))
        if (movie.voteAverage != null) {
            val userRatingStar = resources.getString(
                R.string.movie_user_rating,
                movie.voteAverage
            )
            tvMovieUserRating.setText(userRatingStar)
        } else {
            tvMovieUserRating.setVisibility(View.GONE)
        }
    }

    override fun handleEvent() {
        super.handleEvent()
    }

    override fun observeLiveData() {
        super.observeLiveData()
    }

    override fun onBackPressedCallback() {
        super.onBackPressedCallback()
        if (requireActivity() is MovieDetailsActivity) {
            (requireActivity() as MovieDetailsActivity).finish()
        }
    }
}