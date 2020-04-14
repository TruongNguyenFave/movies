package com.madison.crisis.crisissuppervisor.extention.helper.validation.rule

import com.madison.crisis.crisissuppervisor.extention.helper.validation.isValidUserName

class ExcludeSpecialSymbolRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return isValidUserName(target)
    }
}
