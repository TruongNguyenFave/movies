package com.madison.client.appname.feature.base.dialog

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.madison.client.appname.R
import com.madison.client.appname.feature.base.BaseDialogFragment

/**
 * Created by GianhTran on 15/06/2019.
 * Email: gianhtns.bk@gmail.com
 */
abstract class FullScreenDialogFragment : BaseDialogFragment() {

    protected open val width: Int
        get() = WindowManager.LayoutParams.MATCH_PARENT

    protected open val height: Int
        get() = WindowManager.LayoutParams.MATCH_PARENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogTheme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setLayout(width, height)
    }
}
