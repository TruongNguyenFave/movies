package com.madison.client.appname.extention.helper.validation.rule

import com.madison.client.appname.extention.helper.validation.isValidEmail
import com.madison.client.appname.extention.helper.validation.isValidPhoneNumber

class EmailOrPhoneRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return isValidPhoneNumber(target) || isValidEmail(target)
    }
}
