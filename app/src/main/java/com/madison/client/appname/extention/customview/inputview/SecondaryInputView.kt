package com.madison.client.appname.extention.customview.inputview

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.madison.client.appname.R
import com.madison.client.appname.extention.helper.viewextension.disableCopy
import com.madison.client.appname.extention.helper.viewextension.enableInputHiddenPassword
import com.madison.client.appname.extention.helper.viewextension.hideSoftKeyBoard
import com.madison.client.appname.extention.helper.viewextension.isVisible
import kotlinx.android.synthetic.main.layout_secondary_input_view.view.*

class SecondaryInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    companion object {
        const val INPUT_TYPE_TEXT = "text"
        const val INPUT_TYPE_NUMBER = "number"
        const val INPUT_TYPE_EMAIL = "email"
        const val INPUT_TYPE_PHONE = "phone"
        const val INPUT_TYPE_PASSWORD = "password"
        const val INPUT_TYPE_MULTI_LINE = "multiline"

        const val PRIMARY_STYLE = "primary"
    }

    var onErrorListener: OnSecondaryInputViewErrorListener? = null

    var isTextArea: Boolean? = false
        set(value) {
            // todo confirm about this value
            field = value
        }

    var style: String = PRIMARY_STYLE
        set(value) {
            updateViewStyle(value)
            field = value
        }

    private var inputType: String? = INPUT_TYPE_TEXT
        set(value) {
            when (value) {
                INPUT_TYPE_NUMBER -> {
                    edtInput.inputType = InputType.TYPE_CLASS_NUMBER
                }
                INPUT_TYPE_PASSWORD -> {
                    edtInput.enableInputHiddenPassword()
                    edtInput.disableCopy()
                }
                INPUT_TYPE_PHONE -> {
                    edtInput.inputType = InputType.TYPE_CLASS_PHONE
                }
                INPUT_TYPE_EMAIL -> {
                    edtInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                INPUT_TYPE_MULTI_LINE -> {
                    edtInput.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                    edtInput.gravity = Gravity.START
                    val layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        resources.getDimensionPixelOffset(R.dimen.dp_144)
                    )
                    edtInput.layoutParams = layoutParams
                    edtInput.setPadding(
                        resources.getDimensionPixelOffset(R.dimen.dp_16),
                        resources.getDimensionPixelOffset(R.dimen.dp_16),
                        resources.getDimensionPixelOffset(R.dimen.dp_16),
                        resources.getDimensionPixelOffset(R.dimen.dp_16)
                    )
                    edtInput.isSingleLine = false
                }
                else -> edtInput.inputType = InputType.TYPE_CLASS_TEXT
            }
            field = value
        }

    var hint: String? = null
        set(value) {
            edtInput.hint = value
            field = value
        }

    var errorMsg: String? = null
        set(value) {
            isShowError = !value.isNullOrBlank()
            tvError.text = value
            field = value
        }

    var isShowError: Boolean = false
        set(value) {
            onErrorListener?.onError(value)

            if (value) {
                layoutInput.setBackgroundResource(R.drawable.background_border_error)
            } else {
                updateViewStyle(style)
            }
            field = value
        }

    var maxLength: Int = -1
        set(value) {
            if (value != -1) {
                edtInput.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(value))
            }
            field = value
        }

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_secondary_input_view, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SecondaryInputView)
        try {
            inputType = typedArray.getString(R.styleable.SecondaryInputView_siv_inputType)
            hint = typedArray.getString(R.styleable.SecondaryInputView_siv_hint)
            isTextArea = typedArray.getBoolean(R.styleable.SecondaryInputView_siv_is_area, false)
            style = typedArray.getString(R.styleable.SecondaryInputView_siv_style) ?: PRIMARY_STYLE
            isShowError = false
        } finally {
            typedArray.recycle()
        }
    }

    private fun updateViewStyle(style: String) {
        when (style) {
            PRIMARY_STYLE -> {
                layoutInput.setBackgroundResource(R.drawable.background_border_gray)
            }
            else -> {
                layoutInput.setBackgroundResource(R.drawable.background_border_gray)
            }
        }
    }

    fun hideSoftKeyboardAndClearFocus() {
        edtInput.hideSoftKeyBoard()
    }

    fun getInput(): EditText {
        return edtInput
    }

    fun getText(): String {
        return edtInput.text.toString()
    }

    fun resetValue() {
        if (getText().isNotEmpty()) edtInput.setText("")
    }

    fun setText(text: String) {
        edtInput.setText(text)
    }

    fun isValid(): Boolean {
        return !tvError.isVisible()
    }

    fun handleEnable(isEnable: Boolean) {
        edtInput.isEnabled = isEnable
    }

    interface OnSecondaryInputViewErrorListener {
        fun onError(isError: Boolean)
    }

    fun setBackgroudResource(int: Int) {
        edtInput.setBackgroundResource(int)
    }
}