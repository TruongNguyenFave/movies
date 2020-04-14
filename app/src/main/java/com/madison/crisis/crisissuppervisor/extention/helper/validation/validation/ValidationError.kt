package com.madison.crisis.crisissuppervisor.extention.helper.validation.validation

import android.view.View
import com.madison.crisis.crisissuppervisor.extention.helper.validation.rule.Rule


class ValidationError internal constructor(val view: View, val errorRules: List<Rule>)