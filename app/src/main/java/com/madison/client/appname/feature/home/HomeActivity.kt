package com.madison.client.appname.feature.home

import android.os.Bundle
import com.madison.client.appname.R
import com.madison.client.appname.feature.base.BaseActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
    }
}