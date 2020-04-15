package com.madison.gamuda.supervisor.extention.helper.validation.rule

class CheckedRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return target == true.toString()
    }
}
