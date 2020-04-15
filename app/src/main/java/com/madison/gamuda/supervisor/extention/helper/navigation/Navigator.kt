package com.madison.gamuda.supervisor.extention.helper.navigation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.*
import com.google.android.material.snackbar.Snackbar
import com.madison.gamuda.supervisor.R
import com.madison.gamuda.supervisor.feature.base.BaseActivity
import javax.inject.Inject

class Navigator @Inject constructor() {
    companion object {
        const val TYPE_SNACK_BAR_INFORMATION = "TYPE_SNACK_BAR_INFORMATION"
        const val TYPE_SNACK_BAR_ERROR = "TYPE_SNACK_BAR_ERROR"
        const val TYPE_SNACK_BAR_SUCCESS = "TYPE_SNACK_BAR_SUCCESS"
    }

    fun restartApp(activity: Activity) {
        val i = activity.packageManager.getLaunchIntentForPackage(activity.packageName)
        i?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(i)
    }

    //region Activity
    fun startActivity(activity: Activity, clazz: Class<out Activity>) {
        startActivity(activity, clazz, null)
    }

    fun startActivityAndFinish(activity: Activity, clazz: Class<out Activity>) {
        startActivity(activity, clazz, null)
        activity.finish()
    }

    fun startActivityForResult(activity: Activity, intent: Intent, requestCode: Int) {
        activity.startActivityForResult(intent, requestCode)
    }

    fun startActivityForResultWithFragment(
        fragment: Fragment,
        context: Context,
        clazz: Class<out Activity>,
        requestCode: Int,
        bundle: Bundle? = null
    ) {
        val intent = Intent(context, clazz)
        val animation = ActivityOptions.makeCustomAnimation(
            context, R.anim.slide_in_from_right, R.anim.slide_out_to_left
        ).toBundle()
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        fragment.startActivityForResult(intent, requestCode, animation)
    }

    fun startActivity(activity: Activity, clazz: Class<out Activity>, bundle: Bundle? = null) {
        val intent = Intent(activity, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        activity.startActivity(intent)
        overridePendingTransition(activity)
    }

    fun startActivityForResult(
        context: Context, clazz: Class<out Activity>, bundle: Bundle? = null, requestCode: Int
    ) {
        val intent = Intent(context, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        when (context) {
            is Activity -> context.startActivityForResult(intent, requestCode)
            is Fragment -> context.startActivityForResult(intent, requestCode)
            else -> throw IllegalArgumentException("Context must be activity or fragment")
        }
    }

    fun startActivityAtRoot(activity: Activity, clazz: Class<out Activity>) {
        startActivityAtRoot(activity, clazz, null)
    }

    fun startActivityAtRoot(
        activity: Activity, clazz: Class<out Activity>, bundle: Bundle?
    ) {
        val intent = Intent(activity, clazz)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        activity.startActivity(intent)
        overridePendingTransition(activity)
    }

    fun bringActivityToTop(
        context: Context, clazz: Class<out Activity>, bundle: Bundle?
    ) {
        val intent = Intent(context, clazz)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        context.startActivity(intent)
    }

    fun startNewActivity(activity: Activity, clazz: Class<out Activity>) {
        startActivity(activity, clazz)
        activity.finish()
        overridePendingTransition(activity)
    }

    fun restartActivity(activity: Activity, clazz: Class<out Activity>) {
        activity.finish()
        startActivity(activity, clazz)
        activity.overridePendingTransition(0, 0)
    }

    fun finishActivityWithResult(activity: Activity, intent: Intent, resultCode: Int) {
        activity.setResult(resultCode, intent)
        activity.finish()
        activity.overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        overridePendingTransition(activity)
    }

    fun finishActivityWithTransition(activity: Activity) {
        activity.finish()
        overridePendingTransition(activity)
        activity.overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

    fun startActivityWithTransition(
        activity: Activity, clazz: Class<out Activity>, bundle: Bundle? = null
    ) {
        val intent = Intent(activity, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        activity.startActivity(intent)
        overridePendingTransition(activity)
        activity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
    }

    fun startActivityWithTransitionAtRoot(
        activity: Activity, clazz: Class<out Activity>, bundle: Bundle? = null
    ) {
        startActivityAtRoot(activity, clazz, bundle)
        activity.overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

    private fun overridePendingTransition(activity: Activity) {
        // todo update later
    }

    //endregion

    //region Fragment

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment, tag)
        commitTransaction(transaction, addToBackStack)
    }

    fun removeFragment(
        fragmentManager: FragmentManager, fragment: Fragment
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        transaction.remove(fragment)
        commitTransaction(transaction, false)
    }

    fun replaceFragment(
        activity: Activity,
        containerViewId: Int,
        fragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false
    ) {
        val transaction = (activity as BaseActivity).supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        transaction.replace(containerViewId, fragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun addFragmentWithAnimation(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        transaction.add(frameId, fragment, tag)
        commitTransaction(transaction, addToBackStack)
    }

    fun replaceFragmentAsRoot(
        activity: Activity,
        containerViewId: Int,
        fragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false
    ) {
        popFragmentsToTheRoot(activity)

        replaceFragment(activity, containerViewId, fragment, TAG, addToBackStack)
    }

    fun replaceFragmentWithPopAnimation(
        activity: Activity,
        containerViewId: Int,
        fragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false
    ) {
        val transaction = (activity as BaseActivity).supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_in_from_right,
            R.anim.slide_out_to_left,
            R.anim.slide_in_from_left,
            R.anim.slide_out_to_right
        )
        transaction.replace(containerViewId, fragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun showFragment(
        fragmentManager: FragmentManager, fragment: Fragment, addToBackStack: Boolean = false
    ) {
        val transaction = fragmentManager.beginTransaction()
        // fixme current we have a issue when using custom animation for handleShowNetworkConnectionNotification/hide fragment, so now we don't apply any animation here
        transaction.show(fragment)
        commitTransaction(transaction, addToBackStack)
    }

    fun hideFragment(
        fragmentManager: FragmentManager, fragment: Fragment, addToBackStack: Boolean = false
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.hide(fragment)
        commitTransaction(transaction, addToBackStack)
    }

    fun getCurrentFragment(activity: Activity, @IdRes containerViewId: Int): Fragment? {
        return (activity as FragmentActivity).supportFragmentManager.findFragmentById(
            containerViewId
        )
    }

    fun findFragment(activity: Activity, TAG: String): Fragment? {
        return (activity as FragmentActivity).supportFragmentManager.findFragmentByTag(TAG)
    }

    fun findChildFragment(parentFragment: Fragment, TAG: String): Fragment? {
        return parentFragment.childFragmentManager.findFragmentByTag(TAG)
    }

    fun replaceChildFragment(
        parentFragment: Fragment,
        containerViewId: Int,
        fragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction()
        transaction.replace(containerViewId, fragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun addChildFragment(
        parentFragment: Fragment,
        containerViewId: Int,
        targetFragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction()
        transaction.add(containerViewId, targetFragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun addChildFragmentWithoutAnimation(
        parentFragment: Fragment,
        containerViewId: Int,
        targetFragment: Fragment,
        TAG: String?,
        addToBackStack: Boolean = false
    ) {
        val transaction = parentFragment.childFragmentManager.beginTransaction()
        transaction.add(containerViewId, targetFragment, TAG)
        commitTransaction(transaction, addToBackStack)
    }

    fun popSpecificFragment(activity: Activity, tag: String) {
        (activity as FragmentActivity).supportFragmentManager.popBackStack(
            tag, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    fun popFragmentImmediate(activity: Activity) {
        (activity as FragmentActivity).supportFragmentManager.popBackStackImmediate()
    }

    // fixme remember update later
    fun popFragments(activity: Activity, number: Int) {
        for (i in 0..number) {
            (activity as FragmentActivity).supportFragmentManager.popBackStackImmediate()
        }
    }

    fun popFragment(activity: Activity) {
        (activity as BaseActivity).supportFragmentManager.popBackStack()
    }

    fun backFragment(id: Int, fragment: Fragment, activity: Activity) {
        val transaction = (activity as BaseActivity).supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        transaction.addToBackStack(null)
        transaction.replace(id, fragment)
        transaction.commit()
    }

    fun popFragmentsToTheRoot(activity: Activity) {
        for (i in 0 until (activity as FragmentActivity).supportFragmentManager.backStackEntryCount) {
            activity.supportFragmentManager.popBackStack()
        }
    }

    fun popChildFragment(parentFragment: Fragment) {
        parentFragment.childFragmentManager.popBackStack()
    }

    fun showDialogFragment(
        dialogFragment: DialogFragment, fragmentManager: FragmentManager, tag: String
    ) {
        dialogFragment.show(fragmentManager, tag)
    }

    fun startActivityFromFragment(
        activity: FragmentActivity, clazz: Class<out Activity>, finishActivity: Boolean = true
    ) {
        startActivity(activity, clazz)
        if (finishActivity) activity.finish()
        overridePendingTransition(activity)
    }

    private fun commitTransaction(
        transaction: FragmentTransaction, addToBackStack: Boolean = false
    ) {
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showLastFragmentInBackStack(fragment: Fragment?) {
        fragment?.childFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    //endregion
    fun showNoInternetConnectionSnackbar(activity: Activity) {
        showErrorSnackbar(activity, activity.getString(R.string.no_internet_connection_available))
    }

    fun showNoInternetConnectionSnackbarInDialog(fragmentView: View) {
        showSnackBarInDialog(
            fragmentView, fragmentView.context.getString(R.string.no_internet_connection_available)
        )
    }

    fun showErrorSnackbar(activity: Activity, errorMsg: String) {
        showSnackBar(activity, errorMsg, type = TYPE_SNACK_BAR_ERROR)
    }

    fun showInformationSnackbar(activity: Activity, errorMsg: String) {
        showSnackBar(activity, errorMsg, type = TYPE_SNACK_BAR_INFORMATION)
    }

    fun showSuccessSnackbar(activity: Activity, successMsg: String) {
        showSnackBar(activity, successMsg, type = TYPE_SNACK_BAR_SUCCESS)
    }

    fun showErrorSnackbarInDialog(fragmentView: View, errorMsg: String) {
        showSnackBarInDialog(fragmentView, errorMsg)
    }

    private fun showSnackBarInDialog(
        fragmentView: View,
        message: String,
        action: String? = null,
        actionListener: View.OnClickListener? = null,
        duration: Int = Snackbar.LENGTH_SHORT
    ) {
        val snackBar = Snackbar.make(fragmentView, message, duration)
        if (action != null) {
            snackBar.setAction(action, actionListener)
        }
        snackBar.show()
    }

    private fun showSnackBar(
        activity: Activity,
        message: String,
        action: String? = null,
        actionListener: View.OnClickListener? = null,
        duration: Int = Snackbar.LENGTH_LONG,
        type: String = TYPE_SNACK_BAR_INFORMATION
    ) {
        val snackBar = Snackbar.make(activity.findViewById(android.R.id.content), message, duration)

        val tvMsg =
            snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

        val typeface = ResourcesCompat.getFont(activity, R.font.helvetica_neue_light)
        tvMsg.typeface = typeface

        tvMsg.textSize = 12f
        tvMsg.setTextColor(ContextCompat.getColor(activity, android.R.color.white))

        when (type) {
            TYPE_SNACK_BAR_INFORMATION -> {
                snackBar.view.setBackgroundColor(
                    ContextCompat.getColor(activity, android.R.color.white)
                )
                //                tvMsg.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account, 0, 0, 0)
            }

            TYPE_SNACK_BAR_ERROR -> {
                snackBar.view.setBackgroundColor(
                    ContextCompat.getColor(
                        activity, R.color.alizarinCrimson
                    )
                )
                //                tvMsg.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_close_white, 0, 0, 0)
            }
            //
            TYPE_SNACK_BAR_SUCCESS -> {
                snackBar.view.setBackgroundColor(
                    ContextCompat.getColor(
                        activity, R.color.alizarinCrimson
                    )
                )
                //                tvMsg.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_ticked_white, 0, 0, 0)
            }

            else -> {
                // todo update later
            }
        }

        tvMsg.compoundDrawablePadding = activity.resources.getDimensionPixelOffset(R.dimen.dp_20)

        if (action != null) {
            snackBar.setAction(action, actionListener)
        }
        snackBar.show()
    }
}