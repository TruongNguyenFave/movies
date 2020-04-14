package com.madison.crisis.crisissuppervisor.extention.helper.validation.rule

class RegexRule(errorMessage: String, private val regex: String) : Rule(errorMessage) {
    override fun valid(target: String): Boolean {
        return target.matches(Regex(regex))
    }

}