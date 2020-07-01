package com.madison.client.appname.extention.helper.languagehelper

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView

fun refreshResource(viewGroup: ViewGroup, context: Context) {
    for (i in 0 until viewGroup.childCount) {
        val view = viewGroup.getChildAt(i)
        if (view is ViewGroup) {
            refreshResource(view, context)
        } else {
            view.tag?.let { tag ->
                val resId = try {
                    context.resources.getIdentifier(
                        tag.toString(), "string", context.packageName
                    )
                } catch (ex: RuntimeException) {
                    null
                }

                resId?.let {
                    when (view) {
                        is TextView -> {
                            view.text = context.getString(resId)
                        }
                    }
                }
            }
        }
    }
}