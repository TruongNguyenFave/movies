package com.madison.client.appname.feature.home

import android.os.Bundle
import com.madison.client.appname.feature.base.BaseActivity
import com.madison.client.movies.R
import com.royal.pahang.durian.feature.fruitsupply.fruitsupply.MoviesFragment

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies)

        navigator.addFragment(
            supportFragmentManager, MoviesFragment.newInstance(),
            R.id.contentFrame, false
        )
    }
}