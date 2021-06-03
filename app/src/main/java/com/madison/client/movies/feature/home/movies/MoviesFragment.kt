package com.madison.client.movies.feature.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.madison.client.movies.R
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.databinding.FragmentMoviesBinding
import com.madison.client.movies.feature.base.BaseFragment
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment
import com.madison.client.movies.feature.home.movies.adapter.MovieAdapter
import com.royal.pahang.durian.feature.record.MovieDetailsActivity

class MoviesFragment : BaseFragment() {
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: FragmentMoviesBinding

    private val currentPage = 1

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.viewModel = moviesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initAdapter()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun initView() {
        moviesViewModel.getMovies(currentPage)
    }

    private fun initAdapter() {
        binding.rcvMovies.apply {
            this@MoviesFragment.adapter = MovieAdapter(
                onMovieClickListener
            )
            val gridLayoutManager = GridLayoutManager(
                requireContext(),
                resources.getInteger(R.integer.number_of_grid_columns)
            )
            adapter = this@MoviesFragment.adapter
            layoutManager = gridLayoutManager
        }
    }

    override fun handleEvent() {
        super.handleEvent()
        adapter.setClickLister(object : MovieAdapter.OnClickListener {
            override fun clickItem(movie: Movie) {
                val bundle = Bundle().apply {
                    putParcelable(MovieDetailsFragment.MOVIE_EXTRA_KEY, movie)
                }
                navigator.startActivity(requireActivity(), MovieDetailsActivity::class.java, bundle)
            }
        })
    }

    private val onMovieClickListener: (movie: Movie) -> Unit = { movie ->
        val bundle = Bundle().apply {
            putParcelable(MovieDetailsFragment.MOVIE_EXTRA_KEY, movie)
        }
        navigator.startActivity(requireActivity(), MovieDetailsActivity::class.java, bundle)
    }

    interface Callbacks {
        fun onMovieClick(movie: Movie)
    }
}