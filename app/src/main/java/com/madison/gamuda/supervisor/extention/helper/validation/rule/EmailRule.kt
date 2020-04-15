package com.madison.gamuda.supervisor.extention.helper.validation.rule

import com.madison.gamuda.supervisor.extention.helper.validation.isValidEmail

class EmailRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return isValidEmail(target)
    }
}
