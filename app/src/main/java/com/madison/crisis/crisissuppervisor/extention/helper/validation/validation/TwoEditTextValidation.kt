package com.madison.crisis.crisissuppervisor.extention.helper.validation.validation

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.madison.crisis.crisissuppervisor.extention.helper.validation.validator.Validator

class TwoEditTextValidation(
    observerInputView: EditText,
    subscriberInputView: EditText,
    validator: Validator
) : EditTextValidation(observerInputView, validator) {
    init {
        subscriberInputView.addTextChangedListener(object : TextWatcher {
            var beforeStr: String? = null

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                beforeStr = s?.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
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
}