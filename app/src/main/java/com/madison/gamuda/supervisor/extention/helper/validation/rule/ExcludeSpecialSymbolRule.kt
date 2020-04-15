package com.madison.gamuda.supervisor.extention.helper.validation.rule

import com.madison.gamuda.supervisor.extention.helper.validation.isValidUserName

class ExcludeSpecialSymbolRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return isValidUserName(target)
    }
}
