package com.madison.gamuda.supervisor.extention.helper.permission

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.madison.gamuda.supervisor.R

class RequestPermissionHandler constructor(private val context: Activity) {
    fun requestPermissions(
        permissions: List<String>,
        listener: RequestPermissionHandlerListener?,
        title: String = "",
        msg: String = ""
    ) {
        Dexter.withActivity(context).withPermissions(permissions)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report?.areAllPermissionsGranted() == true) {
                        listener?.onPermissionsGranted()
                    }

                    if (report?.isAnyPermissionPermanentlyDenied == true) {
                        showSettingsDialog(title, msg)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?, token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     */
    private fun showSettingsDialog(title: String, msg: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(context.getString(R.string.go_to_settings)) { dialog, _ ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(context.getString(android.R.string.cancel)) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivityForResult(intent, 101)
    }

    interface RequestPermissionHandlerListener {
        fun onPermissionsGranted()
    }
}