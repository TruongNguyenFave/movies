package com.madison.crisis.crisissuppervisor.extention.helper.validation.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.madison.crisis.crisissuppervisor.extention.helper.validation.validation.ViewValidation
import com.madison.crisis.crisissuppervisor.extention.helper.validation.validator.Validator

open class EditTextValidation(private val editText: EditText, validator: Validator): ViewValidation(editText, validator) {
    var beforeStr: String? = null

    init {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                beforeStr = s?.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            /**
             * onTextChanged will fired when we change inputType so we will prevent validation in this case
             */
            override fun afterTextChanged(s: Editable?) {
                if (s?.toString() != beforeStr) {
                    onViewDataChangeListener.onViewDataChange(view)
                }
            }
        })
    }

    override val validationTarget: String
        get() {
            return editText.text.toString()
        }

    protected fun isValidated(): Boolean {
        return beforeStr != null
    }
}