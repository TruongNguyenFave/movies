package com.madison.crisis.crisissuppervisor.extention.helper.validation.rule

import com.madison.crisis.crisissuppervisor.extention.helper.validation.isValidPhoneNumber

class PhoneRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return isValidPhoneNumber(target)
    }
}

