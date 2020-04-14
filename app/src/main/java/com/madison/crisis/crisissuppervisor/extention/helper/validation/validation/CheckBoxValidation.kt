package com.madison.crisis.crisissuppervisor.extention.helper.validation.validation

import android.widget.CheckBox
import com.madison.crisis.crisissuppervisor.extention.helper.validation.validator.Validator

class CheckBoxValidation(view: CheckBox, validator: Validator) : ViewValidation(view, validator) {

    override val validationTarget: String
        get() = (view as CheckBox).isChecked.toString()

    init {
        view.setOnCheckedChangeListener { _, _ ->
            onViewDataChangeListener.onViewDataChange(view)
        }
    }
}
