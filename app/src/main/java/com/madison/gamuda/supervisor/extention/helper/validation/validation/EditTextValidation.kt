package com.madison.gamuda.supervisor.extention.helper.validation.validation

import android.widget.EditText
import com.madison.gamuda.supervisor.extention.helper.validation.validator.Validator
import com.madison.gamuda.supervisor.extention.helper.viewextension.afterTextChanged

open class EditTextValidation(editText: EditText, validator: Validator) : ViewValidation(
    editText,
    validator
) {

    override val validationTarget: String
        get() = (view as EditText).text.toString()

    init {
        editText.afterTextChanged {
            onViewDataChangeListener.onViewDataChange(view)
        }
    }
}
