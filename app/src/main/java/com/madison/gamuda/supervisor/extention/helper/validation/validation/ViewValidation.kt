package com.madison.gamuda.supervisor.extention.helper.validation.validation

import android.view.View
import com.madison.gamuda.supervisor.extention.helper.validation.OnViewDataChangeListener
import com.madison.gamuda.supervisor.extention.helper.validation.validator.Validator

abstract class ViewValidation(var view: View, private val validator: Validator) {
    lateinit var onViewDataChangeListener: OnViewDataChangeListener
    abstract val validationTarget: String

    fun valid(): ValidationError? {
        val errorRule = validator.valid(validationTarget)
        return if (errorRule.isEmpty()) {
            null
        } else ValidationError(view, errorRule)
    }
}
