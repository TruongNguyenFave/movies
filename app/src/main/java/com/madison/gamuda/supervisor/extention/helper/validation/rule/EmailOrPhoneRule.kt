package com.madison.gamuda.supervisor.extention.helper.validation.rule

import com.madison.gamuda.supervisor.extention.helper.validation.isValidEmail
import com.madison.gamuda.supervisor.extention.helper.validation.isValidPhoneNumber

class EmailOrPhoneRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return isValidPhoneNumber(target) || isValidEmail(target)
    }
}
