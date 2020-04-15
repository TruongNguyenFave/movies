package com.madison.client.appname.extention.customview.button

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.madison.client.appname.R
import com.madison.client.appname.extention.helper.viewextension.handleGoneVisibility
import com.madison.client.appname.extention.helper.viewextension.hideSoftKeyBoard
import com.madison.client.appname.extention.helper.viewextension.safeClick
import kotlinx.android.synthetic.main.layout_primary_button.view.*

class PrimaryButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    companion object {
        const val DEFAULT_STYLE = "default"
        const val SECONDARY_STYLE = "second"
    }

    init {
        init(attrs)
    }

    var listener: OnPrimaryButtonListener? = null

    private var buttonText: String? = null
        set(value) {
            btn.text = value
            field = value
        }

    private var isLoading: Boolean? = null
        set(value) {
            progress.handleGoneVisibility(value == true)
            btn.handleGoneVisibility(value != true)
            field = value
        }

    private var buttonStyle: String? = null
        set(value) {
            updateStyle(value)
            field = value
        }

    private var leftDrawable: Drawable? = null
        set(value) {
            value?.let {
                btn.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null)
            }
            field = value
        }

    fun handleLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun updateButtonText(text: String) {
        btn.text = text
    }

    fun selfClick() {
        layoutContainer.performClick()
    }

    fun handleEnable(isEnabled: Boolean) {
        layoutContainer.isEnabled = isEnabled
        if (isEnabled) {
            updateTextButtonStyle(buttonStyle)
        } else {
            btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        }
        if (!isEnabled) {
            hideLoading()
        }
    }

    fun isLoading(): Boolean {
        return isLoading ?: false
    }

    private fun updateStyle(style: String?) {
        updateTextButtonStyle(style)
        when (style) {
            DEFAULT_STYLE -> {
                layoutContainer.setBackgroundResource(R.drawable.primary_button_background_selector)
                progress.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.white))
                btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
            SECONDARY_STYLE -> {
                layoutContainer.setBackgroundResource(R.drawable.secondary_button_background)
                progress.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.white))
                btn.setTextColor(ContextCompat.getColor(context, R.color.alizarinCrimson))
            }
            else -> {
                layoutContainer.setBackgroundResource(R.drawable.primary_button_background_selector)
                progress.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.white))
                btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }

    private fun updateTextButtonStyle(style: String?) {
        when (style) {
            DEFAULT_STYLE -> {
                btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
            SECONDARY_STYLE -> {
                // todo update content later
            }
            else -> {
                btn.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_primary_button, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PrimaryButton)
        try {
            buttonText = typedArray.getString(R.styleable.PrimaryButton_pb_text)
            buttonStyle = typedArray.getString(R.styleable.PrimaryButton_pb_style) ?: DEFAULT_STYLE
            leftDrawable = typedArray.getDrawable(R.styleable.PrimaryButton_pb_leftDrawable)
        } finally {
            typedArray.recycle()
        }
        handleEvent()
    }

    private fun handleEvent() {
        layoutContainer.safeClick(OnClickListener {
            if (!isLoading()) {
                hideSoftKeyBoard()
                listener?.onClick(it)
            }
        })
    }

    private fun hideLoading() {
        isLoading = false
    }

    interface OnPrimaryButtonListener {
        fun onClick(view: View?)
    }
}