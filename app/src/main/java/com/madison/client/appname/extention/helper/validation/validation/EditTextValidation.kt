package com.madison.client.appname.extention.helper.validation.validation

import android.widget.EditText
import com.madison.client.appname.extention.helper.validation.validator.Validator
import com.madison.client.appname.extention.helper.viewextension.afterTextChanged

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
