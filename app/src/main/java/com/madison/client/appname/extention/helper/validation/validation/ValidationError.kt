package com.madison.client.appname.extention.helper.validation.validation

import android.view.View
import com.madison.client.appname.extention.helper.validation.rule.Rule


class ValidationError internal constructor(val view: View, val errorRules: List<Rule>)