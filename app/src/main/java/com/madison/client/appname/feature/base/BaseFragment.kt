package com.madison.client.appname.feature.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.madison.client.appname.extention.helper.navigation.Navigator
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

    open fun initView() {}

    open fun handleEvent() {}

    open fun observeLiveData() {}

    // todo change to abstract method later
    open fun onBackPressedCallback() {}
}