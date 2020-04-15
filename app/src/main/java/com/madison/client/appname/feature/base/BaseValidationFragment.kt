package com.madison.client.appname.feature.base

import android.os.Bundle
import android.view.View
import com.madison.client.appname.extention.helper.validation.CompositeValidation
import javax.inject.Inject

abstract class BaseValidationFragment: BaseFragment() {

    @Inject
    lateinit var compositeValidation: CompositeValidation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValidation()
    }

    /**
     * When view recreated (eg: back from another screen, app in low memory),
     * we need to reset validation before valid
     */
    open fun initValidation() {
        compositeValidation.reset()
    }
}

