package com.madison.crisis.crisissuppervisor.extention.helper.validation.rule

import com.madison.crisis.crisissuppervisor.extention.helper.validation.isValidEmail

class EmptyEmailRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        if (target.isEmpty()) {
            return true
        }
        return isValidEmail(target)
    }
}
