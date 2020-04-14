package com.madison.crisis.crisissuppervisor.extention.helper.validation.rule

class NoSpaceRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return !target.contains(" ")
    }
}