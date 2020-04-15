package com.madison.gamuda.supervisor.extention.helper.validation.rule

class RangeRule(errorMessage: String, private val min: Int? = null,
    private val max: Int? = null) : Rule(
    errorMessage) {

    override fun valid(target: String): Boolean {
        val len = target.length
        if (min != null && len < min) {
            return false
        }
        return !(max != null && len > max)
    }
}
