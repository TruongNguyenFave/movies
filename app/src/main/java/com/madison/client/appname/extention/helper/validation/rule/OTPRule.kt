package com.madison.client.appname.extention.helper.validation.rule


class OTPRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return !target.contains("-")
    }
}
