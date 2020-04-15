package com.madison.gamuda.supervisor.extention.helper.validation.validation

import android.view.View
import com.madison.gamuda.supervisor.extention.helper.validation.rule.Rule


class ValidationError internal constructor(val view: View, val errorRules: List<Rule>)