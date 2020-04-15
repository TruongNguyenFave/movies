package com.madison.gamuda.supervisor.extention.helper.validation.validator

import com.madison.gamuda.supervisor.extention.helper.validation.rule.Rule
import java.util.*

class Validator {
    private val rules = ArrayList<Rule>()

    fun addRule(rule: Rule): Validator {
        rules.add(rule)
        return this
    }

    fun valid(target: String): List<Rule> {
        val errors = ArrayList<Rule>()
        for (rule in rules) {
            if (!rule.valid(target)) {
                errors.add(rule)
            }
        }
        return errors
    }
}
