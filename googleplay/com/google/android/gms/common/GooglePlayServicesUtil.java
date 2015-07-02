package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.common.util.c;
import com.google.android.gms.common.util.m;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.ke;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Arrays;

public final class GooglePlayServicesUtil {
    public static boolean RU;
    public static boolean RV;
    private static int RW;
    private static final Object RX;

    static {
        RU = false;
        RV = false;
        RW = -1;
        RX = new Object();
    }

    public static void B(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            Intent googlePlayServicesAvailabilityRecoveryIntent = getGooglePlayServicesAvailabilityRecoveryIntent(isGooglePlayServicesAvailable);
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable);
            if (googlePlayServicesAvailabilityRecoveryIntent == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", googlePlayServicesAvailabilityRecoveryIntent);
        }
    }

    private static void E(Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (Throwable e) {
            Log.wtf("GooglePlayServicesUtil", "This should never happen.", e);
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null) {
            int i = bundle.getInt("com.google.android.gms.version");
            if (i != 6587000) {
                throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 6587000 but found " + i + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
            }
            return;
        }
        throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
    }

    private static Dialog a(int i, Activity activity, Fragment fragment, int i2, OnCancelListener onCancelListener) {
        Builder builder;
        Intent googlePlayServicesAvailabilityRecoveryIntent;
        OnClickListener jxVar;
        CharSequence d;
        if (c.M(activity) && i == 2) {
            i = 42;
        }
        if (m.jn()) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new Builder(activity, 5);
                if (builder == null) {
                    builder = new Builder(activity);
                }
                builder.setMessage(c(activity, i));
                if (onCancelListener != null) {
                    builder.setOnCancelListener(onCancelListener);
                }
                googlePlayServicesAvailabilityRecoveryIntent = getGooglePlayServicesAvailabilityRecoveryIntent(i);
                jxVar = fragment != null ? new jx(activity, googlePlayServicesAvailabilityRecoveryIntent, i2) : new jx(fragment, googlePlayServicesAvailabilityRecoveryIntent, i2);
                d = d(activity, i);
                if (d != null) {
                    builder.setPositiveButton(d, jxVar);
                }
                switch (i) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        return null;
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_install_title).create();
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_update_title).create();
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_enable_title).create();
                    case R.styleable.WalletImFormEditText_required /*4*/:
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        return builder.create();
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                        return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_invalid_account_title).create();
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                        Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                        return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_network_error_title).create();
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                        return builder.create();
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                        Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                        return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_unsupported_title).create();
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                        return builder.create();
                    case R.styleable.MapAttrs_uiZoomControls /*11*/:
                        Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                        return builder.create();
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        return builder.setTitle(com.google.android.gms.R.string.common_android_wear_update_title).create();
                    default:
                        Log.e("GooglePlayServicesUtil", "Unexpected error code " + i);
                        return builder.create();
                }
            }
        }
        builder = null;
        if (builder == null) {
            builder = new Builder(activity);
        }
        builder.setMessage(c(activity, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        googlePlayServicesAvailabilityRecoveryIntent = getGooglePlayServicesAvailabilityRecoveryIntent(i);
        if (fragment != null) {
        }
        d = d(activity, i);
        if (d != null) {
            builder.setPositiveButton(d, jxVar);
        }
        switch (i) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return null;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_install_title).create();
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_update_title).create();
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_enable_title).create();
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return builder.create();
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_invalid_account_title).create();
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_network_error_title).create();
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                return builder.create();
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                return builder.setTitle(com.google.android.gms.R.string.common_google_play_services_unsupported_title).create();
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                return builder.create();
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                return builder.create();
            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                return builder.setTitle(com.google.android.gms.R.string.common_android_wear_update_title).create();
            default:
                Log.e("GooglePlayServicesUtil", "Unexpected error code " + i);
                return builder.create();
        }
    }

    public static boolean a(Resources resources) {
        if (resources == null) {
            return false;
        }
        return (m.jk() && ((resources.getConfiguration().screenLayout & 15) > 3)) || b(resources);
    }

    private static byte[] a(PackageInfo packageInfo, boolean z) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        Object toByteArray = packageInfo.signatures[0].toByteArray();
        if ((z ? b.hQ() : b.hR()).contains(toByteArray)) {
            return toByteArray;
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(toByteArray, 0));
        }
        return null;
    }

    private static byte[] a(PackageInfo packageInfo, byte[]... bArr) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        byte[] toByteArray = packageInfo.signatures[0].toByteArray();
        for (byte[] bArr2 : bArr) {
            if (Arrays.equals(bArr2, toByteArray)) {
                return bArr2;
            }
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(toByteArray, 0));
        }
        return null;
    }

    public static boolean b(PackageManager packageManager) {
        synchronized (RX) {
            if (RW == -1) {
                try {
                    if (a(packageManager.getPackageInfo("com.google.android.gms", 64), b.RQ[1]) != null) {
                        RW = 1;
                    } else {
                        RW = 0;
                    }
                } catch (NameNotFoundException e) {
                    RW = 0;
                }
            }
        }
        return RW != 0;
    }

    private static boolean b(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return m.jm() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600;
    }

    public static String c(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return a(context.getResources()) ? resources.getString(com.google.android.gms.R.string.common_google_play_services_install_text_tablet) : resources.getString(com.google.android.gms.R.string.common_google_play_services_install_text_phone);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_update_text);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_enable_text);
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_invalid_account_text);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_network_error_text);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_unsupported_text);
            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                return resources.getString(com.google.android.gms.R.string.common_android_wear_update_text);
            default:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);
        }
    }

    public static String d(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_install_button);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_update_button);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return resources.getString(com.google.android.gms.R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode) {
        return getErrorDialog(errorCode, activity, requestCode, null);
    }

    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode, OnCancelListener cancelListener) {
        return a(errorCode, activity, null, requestCode, cancelListener);
    }

    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int errorCode) {
        switch (errorCode) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return ke.bj("com.google.android.gms");
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return ke.bh("com.google.android.gms");
            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                return ke.iX();
            default:
                return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static boolean hS() {
        return RU ? RV : "user".equals(Build.TYPE);
    }

    public static boolean honorsDebugCertificates(PackageManager packageManager) {
        return b(packageManager) || !hS();
    }

    public static int isGooglePlayServicesAvailable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (!jv.Vz) {
            try {
                context.getResources().getString(com.google.android.gms.R.string.common_google_play_services_unknown_issue);
            } catch (Throwable th) {
                Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
            }
        }
        if (!jv.Vz) {
            E(context);
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            if (c.dB(packageInfo.versionCode)) {
                int i = hS() ? 0 : 1;
                if (a(packageInfo, b.Rm[i], b.Rs[i], b.Rr[i]) == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play Services signature invalid on Glass.");
                    return 9;
                }
                String packageName = context.getPackageName();
                try {
                    PackageInfo packageInfo2 = packageManager.getPackageInfo(packageName, 64);
                    if (!isGoogleSignedPackage(packageManager, packageInfo2)) {
                        Log.w("GooglePlayServicesUtil", "Calling package " + packageInfo2.packageName + " signature invalid on Glass.");
                        return 9;
                    }
                } catch (NameNotFoundException e) {
                    Log.w("GooglePlayServicesUtil", "Could not get info for calling package: " + packageName);
                    return 9;
                }
            } else if (!c.M(context)) {
                try {
                    if (a(packageManager.getPackageInfo("com.android.vending", 64), b.Rm) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (a(packageInfo, a(packageManager.getPackageInfo("com.android.vending", 64), b.Rm)) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (NameNotFoundException e2) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                    return 9;
                }
            } else if (a(packageInfo, b.Rm) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (c.dz(packageInfo.versionCode) < c.dz(6587000)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires 6587000 but found " + packageInfo.versionCode);
                return 2;
            }
            try {
                return !packageManager.getApplicationInfo("com.google.android.gms", 0).enabled ? 3 : 0;
            } catch (NameNotFoundException e3) {
                Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.");
                e3.printStackTrace();
                return 1;
            }
        } catch (NameNotFoundException e4) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    public static boolean isGoogleSignedPackage(PackageManager packageManager, PackageInfo packageInfo) {
        boolean z = true;
        boolean z2 = false;
        if (packageInfo == null) {
            return false;
        }
        if (honorsDebugCertificates(packageManager)) {
            if (a(packageInfo, true) == null) {
                z = false;
            }
            return z;
        }
        if (a(packageInfo, false) != null) {
            z2 = true;
        }
        if (z2 || a(packageInfo, true) == null) {
            return z2;
        }
        Log.w("GooglePlayServicesUtil", "Test-keys aren't accepted on this build.");
        return z2;
    }

    public static boolean isPlayServicesPossiblyUpdating(Context context, int connectionStatusCode) {
        if (connectionStatusCode == 1) {
            try {
                if (context.getPackageManager().getApplicationInfo("com.google.android.gms", 8192).enabled) {
                    return true;
                }
            } catch (NameNotFoundException e) {
            }
        }
        return false;
    }

    public static boolean isSidewinderDevice(Context context) {
        return context.getPackageManager().hasSystemFeature("com.google.sidewinder");
    }

    public static boolean isUserRecoverableError(int errorCode) {
        switch (errorCode) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode) {
        return showErrorDialogFragment(errorCode, activity, requestCode, null);
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode, OnCancelListener cancelListener) {
        return showErrorDialogFragment(errorCode, activity, null, requestCode, cancelListener);
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, Fragment fragment, int requestCode, OnCancelListener cancelListener) {
        boolean z = false;
        Dialog a = a(errorCode, activity, fragment, requestCode, cancelListener);
        if (a == null) {
            return z;
        }
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
        }
        if (z) {
            SupportErrorDialogFragment.newInstance(a, cancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), "GooglePlayServicesErrorDialog");
        } else if (m.jk()) {
            ErrorDialogFragment.newInstance(a, cancelListener).show(activity.getFragmentManager(), "GooglePlayServicesErrorDialog");
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
        return true;
    }
}
