package com.madison.client.appname.feature.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.madison.client.appname.R
import com.madison.client.appname.data.repository.local.api.AccessTokenWrapper
import com.madison.client.appname.feature.auth.signin.SignInFragment
import com.madison.client.appname.feature.base.BaseActivity
import com.madison.client.appname.feature.home.HomeActivity
import javax.inject.Inject

class AuthActivity : BaseActivity() {
    companion object {
        const val KEY_AUTH_EXTRAS = "KEY_AUTH_EXTRAS"
    }

    @Inject
    lateinit var accessTokenWrapper: AccessTokenWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_auth)

        if (accessTokenWrapper.getAccessToken().isNullOrEmpty() or intent.hasExtra(KEY_AUTH_EXTRAS)) {
            accessTokenWrapper.clearData()

            goToSignInScreen()
        } else {
            navigator.startActivityAtRoot(this@AuthActivity, HomeActivity::class.java)
        }
    }

    private fun goToSignInScreen() {
        var signInFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (signInFragment == null) {
            signInFragment = SignInFragment.newInstance()
            navigator.addFragment(
                supportFragmentManager, signInFragment, R.id.contentFrame
            )
        }
    }
}