package com.madison.client.appname.feature.base.dialog

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.madison.client.appname.R
import com.madison.client.appname.feature.base.BaseDialogFragment

abstract class FullWidthDialogFragment : BaseDialogFragment() {

    protected open val width: Int
        get() = WindowManager.LayoutParams.MATCH_PARENT

    protected open val height: Int
        get() = WindowManager.LayoutParams.WRAP_CONTENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogTheme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setLayout(width, height)
    }
}
