package com.royal.pahang.durian.feature.fruitsupply.fruitsupply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.madison.client.appname.feature.base.BaseFragment
import com.madison.client.movies.R

class MoviesFragment : BaseFragment() {
    private lateinit var moviesViewModel: MoviesViewModel

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
        moviesViewModel.getMovies()
    }

    private fun initAdapter() {

    }

    override fun handleEvent() {
        super.handleEvent()
    }
}