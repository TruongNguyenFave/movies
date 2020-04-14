package com.madison.crisis.crisissuppervisor.extention.helper.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.IBinder
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by mac on 9/22/17.
 */

class DisplayUtils {
    companion object {
        val SCREEN_SIZE = IntArray(2)

        fun initDisplay(activity: Activity) {
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            SCREEN_SIZE[0] = size.x
            SCREEN_SIZE[1] = size.y
        }

        fun showKeyboard(c: Context, editText: EditText) {
            val imm = c.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }

        fun closeKeyboard(c: Context, windowToken: IBinder) {
            try {
                val mgr = c.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mgr.hideSoftInputFromWindow(windowToken, 0)
            } catch (e: Exception) {
                // handle exception
            }

        }

        fun convertDpToPx(context: Context, dp: Float): Int {
            val r = context.resources

            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.displayMetrics
            ).toInt()
        }

        fun getScreenWidth(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }
    }
}
