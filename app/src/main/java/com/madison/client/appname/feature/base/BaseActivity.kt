package com.madison.client.appname.feature.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import com.madison.client.appname.data.repository.remote.api.error.RetrofitException
import com.madison.client.appname.data.repository.remote.api.error.UNAUTHORIZED_CODE
import com.madison.client.appname.extention.helper.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    open fun handleShowServerError(error: RetrofitException) {
        if (error.isNetworkError()) {
            // todo show message in case a network error occur
        } else {
            if (error.getErrorCode() == UNAUTHORIZED_CODE) {
                // todo handle case unauthorized
                return
            }
            // todo update show error method later
            Toast.makeText(this, error.getErrorMessage(), Toast.LENGTH_SHORT).show()
        }
    }
}

