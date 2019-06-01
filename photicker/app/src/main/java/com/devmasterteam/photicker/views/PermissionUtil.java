package com.devmasterteam.photicker.views;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.devmasterteam.photicker.R;

class PermissionUtil {
    public static int CAMERA_PERMISSION;

    public static boolean hasCameraPermission(Context context) {
        if(needToAksPermission()){
            return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private static boolean needToAksPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public static void asksCameraPermission(final MainActivity mainActivity) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(mainActivity, Manifest.permission.CAMERA)){
            new AlertDialog.Builder(mainActivity)
                    .setMessage(R.string.permission_camera_explanation)
                    .setPositiveButton(R.string.permission_camera_explanation, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(mainActivity, new String[]{
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            },PermissionUtil.CAMERA_PERMISSION);
                        }
                    });

        }else{
            ActivityCompat.requestPermissions(mainActivity, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },PermissionUtil.CAMERA_PERMISSION);
        }
    }
}
