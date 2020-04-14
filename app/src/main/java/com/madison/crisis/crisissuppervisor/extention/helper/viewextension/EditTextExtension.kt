package com.madison.crisis.crisissuppervisor.extention.helper.viewextension

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText

internal const val INPUT_TYPE_VISIBLE_PASSWORD =
    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
internal const val INPUT_TYPE_HIDDEN_PASSWORD = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

fun EditText.onTextChanged(onTextChanged: (CharSequence) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged.invoke(p0 ?: "")
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    })
}

fun EditText.afterTextChanged(afterTextChanged: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable)
        }
    })
}

fun EditText.onFocusChaned(onFocusChanged: (Boolean) -> Unit) {
    this.onFocusChangeListener = View.OnFocusChangeListener { p0, isFocus ->
        onFocusChanged.invoke(isFocus)
    }
}

fun EditText.disableCopy() {
    customSelectionActionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu): Boolean {
            return false
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {}
    }
    setTextIsSelectable(false)
    isLongClickable = false
}

fun EditText.isPasswordVisible(): Boolean {
    return inputType == INPUT_TYPE_VISIBLE_PASSWORD
}

fun EditText.enableInputVisiblePassword() {
    val cache = typeface
    inputType = INPUT_TYPE_VISIBLE_PASSWORD
    typeface = cache
}

fun EditText.enableInputHiddenPassword() {
    val cache = typeface
    inputType = INPUT_TYPE_HIDDEN_PASSWORD
    typeface = cache
}

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}