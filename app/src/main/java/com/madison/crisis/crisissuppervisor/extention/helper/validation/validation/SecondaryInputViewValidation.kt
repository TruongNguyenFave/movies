package com.madison.crisis.crisissuppervisor.extention.helper.validation.validation

import android.text.Editable
import android.text.TextWatcher
import com.madison.crisis.crisissuppervisor.extention.customview.inputview.SecondaryInputView
import com.madison.crisis.crisissuppervisor.extention.helper.validation.validator.Validator

open class SecondaryInputViewValidation(
    private val secondaryInputView: SecondaryInputView, validator: Validator
) : ViewValidation(secondaryInputView, validator) {
    var beforeStr: String? = null

    init {
        secondaryInputView.getInput().addTextChangedListener(object : TextWatcher {
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
            return secondaryInputView.getInput().text.toString()
        }

    protected fun isValidated(): Boolean {
        return beforeStr != null
    }
}
