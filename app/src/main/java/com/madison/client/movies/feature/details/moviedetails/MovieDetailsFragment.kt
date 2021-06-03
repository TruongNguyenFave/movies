package com.madison.client.movies.feature.details.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.databinding.FragmentMovieDetailBinding
import com.madison.client.movies.feature.base.BaseFragment
import com.royal.pahang.durian.feature.record.MovieDetailsActivity

class MovieDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieDetailBinding

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
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun initView() {
        arguments?.let {
            if (it.containsKey(MOVIE_EXTRA_KEY)) {
                it.getParcelable<Movie>(MOVIE_EXTRA_KEY)?.let {
                    binding.movie = it
                    binding.executePendingBindings()
                }
            }
        }
    }

    override fun onBackPressedCallback() {
        super.onBackPressedCallback()
        if (requireActivity() is MovieDetailsActivity) {
            (requireActivity() as MovieDetailsActivity).finish()
        }
    }
}