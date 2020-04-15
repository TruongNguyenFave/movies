package com.madison.gamuda.supervisor.extention.helper.validation.rule

import android.text.TextUtils

class NoneEmptyRule(errorMessage: String) : Rule(errorMessage) {

    override fun valid(target: String): Boolean {
        return !TextUtils.isEmpty(target.trim())
    }
}
