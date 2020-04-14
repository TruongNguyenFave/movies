package com.madison.crisis.crisissuppervisor.extention.helper.validation.rule

abstract class Rule(val error: String) {

    abstract fun valid(target: String): Boolean
}
