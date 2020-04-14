package com.madison.crisis.crisissuppervisor.extention.helper.permission

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.madison.crisis.crisissuppervisor.R

/**
 * Handles the requesting of the camera permission.  This includes
 * showing a "Snackbar" message of why the permission is needed then
 * sending the request.
 */
const val OPEN_APP_SETTING_REQUEST_CODE: Int = 100

fun requestPermission(activity: Activity, permissions: Array<String>, requestCode: Int, snackBarView: View) {

    if (!ActivityCompat.shouldShowRequestPermissionRationale(
            activity,
            Manifest.permission.CAMERA
        )
    ) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
        return
    }

    val listener = View.OnClickListener {
        ActivityCompat.requestPermissions(
            activity, permissions,
            requestCode
        )
    }

    Snackbar.make(
        snackBarView, R.string.permission_request_message,
        Snackbar.LENGTH_INDEFINITE
    )
        .setAction(android.R.string.ok, listener)
        .show()
}

/**
 * Showing Alert Dialog with Settings option
 * Navigates user to app settings
 */
fun showSettingsDialog(packageName: String, context: Activity) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(context.getString(R.string.dialog_permission_title))
    builder.setMessage(context.getString(R.string.dialog_permission_message))
    builder.setPositiveButton(context.getString(R.string.go_to_settings)) { dialog, _ ->
        dialog.cancel()
        openSettings(packageName, context)
    }
    builder.setNegativeButton(context.getString(android.R.string.cancel)) { dialog, _ -> dialog.cancel() }
    builder.show()
}

// navigating user to app settings
private fun openSettings(packageName: String, activity: Activity) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    activity.startActivityForResult(intent, OPEN_APP_SETTING_REQUEST_CODE)
}
