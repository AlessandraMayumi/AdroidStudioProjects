package com.example.octolauncher;

import android.graphics.drawable.Drawable;

public class ApkInfo {
    private CharSequence apkLabel, apkPackage;
    private Drawable apkIcon;

    public CharSequence getApkLabel() {
        return apkLabel;
    }

    public void setApkLabel(CharSequence apkLabel) {
        this.apkLabel = apkLabel;
    }

    public CharSequence getApkPackage() {
        return apkPackage;
    }

    public void setApkPackage(CharSequence apkPackage) {
        this.apkPackage = apkPackage;
    }

    public Drawable getApkIcon() {
        return apkIcon;
    }

    public void setApkIcon(Drawable apkIcon) {
        this.apkIcon = apkIcon;
    }
}
