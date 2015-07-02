package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ErrorReport implements SafeParcelable {
    public static final Creator<ErrorReport> CREATOR;
    public String account;
    public String anrStackTraces;
    public ApplicationErrorReport applicationErrorReport;
    public BitmapTeleporter bitmapTeleporter;
    public String board;
    public String brand;
    public String buildFingerprint;
    public String buildId;
    public String buildType;
    public String categoryTag;
    public String codename;
    public String color;
    public String description;
    public String device;
    public String[] eventLog;
    public String exceptionClassName;
    public String exceptionMessage;
    public boolean excludePii;
    public FileTeleporter[] fileTeleporterList;
    public String incremental;
    public boolean isCtlAllowed;
    public boolean isSilentSend;
    public String launcher;
    public String localeString;
    public String model;
    public int networkMcc;
    public int networkMnc;
    public String networkName;
    public int networkType;
    public int packageVersion;
    public String packageVersionName;
    public int phoneType;
    public String product;
    public Bundle psdBundle;
    public String[] psdFilePaths;
    public String release;
    public String[] runningApplications;
    public String screenshot;
    public byte[] screenshotBytes;
    public int screenshotHeight;
    public String screenshotPath;
    public int screenshotWidth;
    public int sdk_int;
    public String stackTrace;
    public String submittingPackageName;
    public String[] systemLog;
    public String throwClassName;
    public String throwFileName;
    public int throwLineNumber;
    public String throwMethodName;
    public final int versionCode;

    static {
        CREATOR = new a();
    }

    public ErrorReport() {
        this.applicationErrorReport = new ApplicationErrorReport();
        this.versionCode = 5;
    }

    ErrorReport(int versionCode, ApplicationErrorReport applicationErrorReport, String description, int packageVersion, String packageVersionName, String device, String buildId, String buildType, String model, String product, String buildFingerprint, int sdk_int, String release, String incremental, String codename, String board, String brand, String[] runningApplications, String[] systemLog, String[] eventLog, String anrStackTraces, String screenshot, byte[] screenshotBytes, int screenshotHeight, int screenshotWidth, int phoneType, int networkType, String networkName, String account, String localeString, Bundle psdBundle, boolean isSilentSend, int networkMcc, int networkMnc, boolean isCtlAllowed, String exceptionClassName, String throwFileName, int throwLineNumber, String throwClassName, String throwMethodName, String stackTrace, String exceptionMessage, String categoryTag, String color, String submittingPackageName, BitmapTeleporter bitmapTeleporter, String screenshotPath, FileTeleporter[] fileTeleporterList, String[] psdFilePaths, boolean excludePii, String launcher) {
        this.applicationErrorReport = new ApplicationErrorReport();
        this.versionCode = versionCode;
        this.applicationErrorReport = applicationErrorReport;
        this.description = description;
        this.packageVersion = packageVersion;
        this.packageVersionName = packageVersionName;
        this.device = device;
        this.buildId = buildId;
        this.buildType = buildType;
        this.model = model;
        this.product = product;
        this.buildFingerprint = buildFingerprint;
        this.sdk_int = sdk_int;
        this.release = release;
        this.incremental = incremental;
        this.codename = codename;
        this.board = board;
        this.brand = brand;
        this.runningApplications = runningApplications;
        this.systemLog = systemLog;
        this.eventLog = eventLog;
        this.anrStackTraces = anrStackTraces;
        this.screenshot = screenshot;
        this.screenshotBytes = screenshotBytes;
        this.screenshotHeight = screenshotHeight;
        this.screenshotWidth = screenshotWidth;
        this.phoneType = phoneType;
        this.networkType = networkType;
        this.networkName = networkName;
        this.account = account;
        this.localeString = localeString;
        this.psdBundle = psdBundle;
        this.isSilentSend = isSilentSend;
        this.networkMcc = networkMcc;
        this.networkMnc = networkMnc;
        this.isCtlAllowed = isCtlAllowed;
        this.exceptionClassName = exceptionClassName;
        this.throwFileName = throwFileName;
        this.throwLineNumber = throwLineNumber;
        this.throwClassName = throwClassName;
        this.throwMethodName = throwMethodName;
        this.stackTrace = stackTrace;
        this.exceptionMessage = exceptionMessage;
        this.categoryTag = categoryTag;
        this.color = color;
        this.submittingPackageName = submittingPackageName;
        this.bitmapTeleporter = bitmapTeleporter;
        this.screenshotPath = screenshotPath;
        this.fileTeleporterList = fileTeleporterList;
        this.psdFilePaths = psdFilePaths;
        this.excludePii = excludePii;
        this.launcher = launcher;
    }

    private static Bitmap a(Bitmap bitmap, int i, int i2) {
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }

    public int describeContents() {
        return 0;
    }

    public void setScreenshot(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            Log.e("ErrorReport", "Bitmap is null or recycled. Cant compress. We will not attach screenshot");
            return;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 70, byteArrayOutputStream);
        this.screenshot = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        this.screenshotWidth = bitmap.getWidth();
        this.screenshotHeight = bitmap.getHeight();
        if (this.screenshot.getBytes().length > 262144) {
            Log.d("ErrorReport", "Encountered large screenshot, size: " + this.screenshot.getBytes().length + ",  compressing further.");
            setScreenshot(a(bitmap, this.screenshotWidth >> 1, this.screenshotHeight >> 1));
        }
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
