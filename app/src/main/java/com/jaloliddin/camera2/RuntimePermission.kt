package com.jaloliddin.camera2

import android.Manifest
import android.content.Context
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class RuntimePermission {

    fun permissionListForCamera(onRunTimePermissionListener: OnRunTimePermissionListener, context: Context) {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    onRunTimePermissionListener.onPermissionGranted()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {
                    onRunTimePermissionListener.onPermissionDenied()
                }
            }).check()
    }
}

interface OnRunTimePermissionListener {

    //onPermission Granted...
    fun onPermissionGranted()

    //onPermissionDenied
    fun onPermissionDenied()
}