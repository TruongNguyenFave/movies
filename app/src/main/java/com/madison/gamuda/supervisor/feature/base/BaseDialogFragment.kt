package com.madison.gamuda.supervisor.feature.base

import android.os.Bundle
import android.view.View
import com.madison.gamuda.supervisor.extention.helper.navigation.Navigator
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

abstract class BaseDialogFragment : DaggerDialogFragment() {
    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleEvent()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    open fun initView() {}

    open fun handleEvent() {}

    open fun observe() {}
}