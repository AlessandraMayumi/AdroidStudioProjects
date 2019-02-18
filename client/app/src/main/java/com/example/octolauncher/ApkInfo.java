package com.example.octolauncher;

import android.graphics.drawable.Drawable;

public class ApkInfo {
    private CharSequence apkLabel, apkInfo, apkPackage;
    private Drawable apkIcon;
//    public ApkInfo(String apkLabel, String apkInfo, String apkPackage, Drawable apkIcon) {
//        this.apkInfo = apkInfo;
//        this.apkLabel = apkLabel;
//        this.apkIcon = apkIcon;
//        this.apkPackage = apkPackage;
//    }

    public CharSequence getApkLabel() {
        return apkLabel;
    }

    public void setApkLabel(CharSequence apkLabel) {
        this.apkLabel = apkLabel;
    }

    public CharSequence getApkInfo() {
        return apkInfo;
    }

    public void setApkInfo(CharSequence apkInfo) {
        this.apkInfo = apkInfo;
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
