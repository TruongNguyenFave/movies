package com.madison.client.movies.feature.home.movies

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madison.client.movies.R
import com.madison.client.movies.data.model.Category
import com.madison.client.movies.data.model.Movie
import com.madison.client.movies.databinding.FragmentMoviesBinding
import com.madison.client.movies.feature.base.BaseFragment
import com.madison.client.movies.feature.details.MovieDetailsActivity
import com.madison.client.movies.feature.details.moviedetails.MovieDetailsFragment
import com.madison.client.movies.feature.home.movies.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : BaseFragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: FragmentMoviesBinding

    private val onMovieClickListener: (movie: Movie) -> Unit = { movie ->
        goToMovieDetailActivity(movie)
    }

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
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
        fetchMoviesFromFirstPage()
    }

    private fun initAdapter() {
        binding.rcvMovies.apply {
            this@MoviesFragment.adapter = MovieAdapter(onMovieClickListener)
            val gridLayoutManager = GridLayoutManager(
                requireContext(), resources.getInteger(R.integer.number_of_grid_columns)
            )
            adapter = this@MoviesFragment.adapter
            layoutManager = gridLayoutManager
        }
        binding.rcvMovies.addOnScrollListener(scrollListener)
    }

    // scroll to get Next Page of result
    private val scrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    moviesViewModel.fetchMoviesOfNextPage()
                }
            }
        }

    private fun fetchMoviesFromFirstPage() {
        moviesViewModel.resetPageNumber()
        moviesViewModel.fetchMovieList()
    }

    override fun handleEvent() {
        super.handleEvent()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            fetchMoviesFromFirstPage()
        }
    }

    private fun goToMovieDetailActivity(movie: Movie) {
        val bundle = Bundle().apply {
            putParcelable(MovieDetailsFragment.MOVIE_EXTRA_KEY, movie)
        }
        navigator.startActivity(requireActivity(), MovieDetailsActivity::class.java, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.order_by_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_release_date -> {
                moviesViewModel.apply {
                    sortBy = Category.RELEASE_DATE.category
                }
                fetchMoviesFromFirstPage()
                true
            }
            R.id.menu_item_alphabetical -> {
                moviesViewModel.apply {
                    sortBy = Category.ALPHABETICAL.category
                }
                fetchMoviesFromFirstPage()
                true
            }
            R.id.menu_item_rating -> {
                moviesViewModel.apply {
                    sortBy = Category.RATING.category
                }
                fetchMoviesFromFirstPage()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressedCallback() {
        requireActivity().finish()
    }
}