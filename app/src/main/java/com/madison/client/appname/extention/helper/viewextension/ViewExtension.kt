package com.madison.client.appname.extention.helper.viewextension

import android.os.SystemClock
import android.view.View

fun View.safeClick(listener: View.OnClickListener, blockInMillis: Long = 500) {
    var lastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < blockInMillis) return@setOnClickListener
        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClick(this)
    }
}

fun View.handleGoneVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}