package com.madison.gamuda.supervisor.feature.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.madison.gamuda.supervisor.R
import com.madison.gamuda.supervisor.data.repository.remote.api.error.RetrofitException
import com.madison.gamuda.supervisor.data.repository.remote.api.error.UNAUTHORIZED_CODE
import com.madison.gamuda.supervisor.extention.helper.navigation.Navigator
import com.madison.gamuda.supervisor.feature.auth.AuthActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                onBackPressedCallback()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleEvent()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLiveData()
    }

    open fun handleShowServerError(error: RetrofitException) {
        if (error.isNetworkError()) {
            navigator.showErrorSnackbar(
                requireActivity(), getString(R.string.no_internet_connection_available)
            )
        } else {
            if (error.getErrorCode() == UNAUTHORIZED_CODE) {
                val bundle = Bundle()
                bundle.putBoolean(AuthActivity.KEY_AUTH_EXTRAS, true)

                navigator.startActivityAtRoot(
                    requireActivity(), AuthActivity::class.java, bundle
                )

                return
            }
            navigator.showErrorSnackbar(requireActivity(), error.getErrorMessage() ?: "")
        }
    }

    fun showMessageNotImplementYet() {
        // only using for testing mode
        navigator.showErrorSnackbar(requireActivity(), "This function not available")
    }

    open fun initView() {}

    open fun handleEvent() {}

    open fun observeLiveData() {}

    // todo change to abstract method later
    open fun onBackPressedCallback() {}
}