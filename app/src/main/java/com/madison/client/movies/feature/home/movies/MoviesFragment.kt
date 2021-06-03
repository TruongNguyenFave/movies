package com.royal.pahang.durian.feature.fruitsupply.fruitsupply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.madison.client.movies.R
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.extention.helper.viewextension.handleGoneVisibility
import com.madison.client.movies.feature.base.BaseFragment
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment
import com.madison.client.movies.feature.home.movies.adapter.MoviesAdapter
import com.royal.pahang.durian.feature.record.MovieDetailsActivity
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : BaseFragment() {
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: MoviesAdapter

    private val currentPage = 1

    companion object {
        fun newInstance(): MoviesFragment {
            val fragment = MoviesFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun initView() {
        initAdapter()
        moviesViewModel.getMovies(currentPage)
    }

    private fun initAdapter() {
        adapter = MoviesAdapter(requireContext())
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            resources.getInteger(R.integer.number_of_grid_columns)
        )
        rcvMovies.adapter = adapter
        rcvMovies.layoutManager = gridLayoutManager
    }

    override fun handleEvent() {
        super.handleEvent()
        adapter.setClickLister(object : MoviesAdapter.OnClickListener {
            override fun clickItem(movie: Movie) {
                val bundle = Bundle().apply {
                    putParcelable(MovieDetailsFragment.MOVIE_EXTRA_KEY, movie)
                }
                navigator.startActivity(requireActivity(), MovieDetailsActivity::class.java, bundle)
            }
        })
    }

    override fun observeLiveData() {
        super.observeLiveData()
        moviesViewModel.isGettingMoviesObservable.observe(
            viewLifecycleOwner,
            Observer {
                progressBar.handleGoneVisibility(it)
            })
        moviesViewModel.getMoviesSuccessObservable.observe(
            viewLifecycleOwner,
            Observer {
                if (!it.results.isNullOrEmpty()) {
                    adapter.injectData(it.results)
                }
            })
        moviesViewModel.getMoviesErrorObservable.observe(
            viewLifecycleOwner,
            Observer {

            })
    }
}