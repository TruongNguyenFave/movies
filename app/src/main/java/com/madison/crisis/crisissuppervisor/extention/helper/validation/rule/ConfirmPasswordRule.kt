package com.madison.crisis.crisissuppervisor.extention.helper.validation.rule

import android.widget.EditText

class ConfirmPasswordRule(errorMessage: String, private val passwordInputView: EditText) : Rule(
    errorMessage) {

    override fun valid(target: String): Boolean {
        return target == passwordInputView.text.toString()
    }
}