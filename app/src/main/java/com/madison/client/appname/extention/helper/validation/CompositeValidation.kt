package com.madison.client.appname.extention.helper.validation

import android.annotation.SuppressLint
import android.view.View
import com.madison.client.appname.extention.helper.validation.validation.ValidationError
import com.madison.client.appname.extention.helper.validation.validation.ViewValidation
import java.util.*
import javax.inject.Inject

@SuppressLint("UseSparseArrays")
class CompositeValidation @Inject constructor() : OnViewDataChangeListener {
    private val validations = HashMap<Int, ViewValidation>()
    private val validationSuccessViews = HashSet<View>()
    private var listener: CompositeValidationListener? = null

    val isAllValidationSuccess: Boolean
        get() = validationSuccessViews.size == validations.size

    fun add(validation: ViewValidation) {
        validations[validation.view.id] = validation
        validation.onViewDataChangeListener = this
    }

    /**
     *
     * Validate all field width its rules and notify newest success and error
     */
    fun validateAll() {
        val newErrors = ArrayList<ValidationError>()
        val newSuccess = ArrayList<View>()
        for (validation in validations.values) {
            val error = validation.valid()
            if (error == null) {
                newSuccess.add(validation.view)
                validationSuccessViews.add(validation.view)
            } else {
                newErrors.add(error)
                validationSuccessViews.remove(validation.view)
            }
        }
        notifyValidationChanged(newSuccess, newErrors)
    }

    fun setListener(listener: CompositeValidationListener) {
        this.listener = listener
    }

    fun reset() {
        validations.clear()
        validationSuccessViews.clear()
        listener = null
    }


    /**
     * Validate the current changed field and return only success and error for this field
     */
    override fun onViewDataChange(view: View) {
        val error = getValidation(view).valid()
        if (error == null) {
            validationSuccessViews.add(view)
            notifyValidationChanged(object : ArrayList<View>() {
                init {
                    add(view)
                }
            }, null)
        } else {
            validationSuccessViews.remove(view)
            notifyValidationChanged(null, object : ArrayList<ValidationError>() {
                init {
                    add(error)
                }
            })
        }
    }

    private fun notifyValidationChanged(newSuccess: List<View>?, newErrors: List<ValidationError>?) {
        if (newErrors != null) {
            listener?.onValidationError(newErrors)
        }
        if (newSuccess != null) {
            listener?.onValidationSuccess(newSuccess)
        }
        if (isAllValidationSuccess) {
            listener?.onAllValidationSuccess()
        }
    }

    /**
     * Return validation by viewId
     */
    private fun getValidation(view: View): ViewValidation {
        return validations[view.id]!!
    }

    interface CompositeValidationListener {
        /**
         * @param views validation success views
         */
        fun onValidationSuccess(views: List<View>)

        fun onValidationError(errors: List<ValidationError>)

        fun onAllValidationSuccess()
    }
}