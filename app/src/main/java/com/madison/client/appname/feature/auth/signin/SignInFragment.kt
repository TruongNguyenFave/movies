package com.madison.client.appname.feature.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.madison.client.appname.R
import com.madison.client.appname.feature.base.BaseValidationFragment

class SignInFragment : BaseValidationFragment() {
    private lateinit var signInViewModel: SignInViewModel

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        signInViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }
}