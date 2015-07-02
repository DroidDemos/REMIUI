package com.google.android.vending.verifier;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public class VerifyInstalledPackagesReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.google.android.vending.verifier.intent.action.VERIFY_INSTALLED_PACKAGES".equals(action)) {
            if (checkPrerequisites(context)) {
                FinskyLog.d("Verify installed apps requested", new Object[0]);
                PackageVerificationService.start(context, intent);
            }
        } else if ("com.google.android.vending.verifier.intent.action.REMOVAL_REQUEST_RESPONSE".equals(action)) {
            FinskyLog.d("Handle removal request responses requested", new Object[0]);
            PackageVerificationService.start(context, intent);
        } else {
            FinskyLog.w("Unexpected action %s", action);
        }
    }

    public static void verifyInstalledPackages(Context context) {
        context.sendBroadcast(new Intent("com.google.android.vending.verifier.intent.action.VERIFY_INSTALLED_PACKAGES"));
    }

    private boolean checkPrerequisites(Context context) {
        if (!((Boolean) G.platformAntiMalwareEnabled.get()).booleanValue()) {
            FinskyLog.d("Skipping verification because disabled", new Object[0]);
            return false;
        } else if (!FinskyApp.get().getInstallPolicies().hasNetwork()) {
            FinskyLog.d("Skipping verification because network inactive", new Object[0]);
            return false;
        } else if (isPackageVerifierEnabled(context)) {
            return true;
        } else {
            FinskyLog.d("Skipping verification because verify apps is not enabled", new Object[0]);
            return false;
        }
    }

    private static boolean isPackageVerifierEnabled(Context context) {
        int value;
        ContentResolver resolver = context.getContentResolver();
        if (VERSION.SDK_INT >= 17) {
            value = Global.getInt(resolver, "package_verifier_enable", 1);
        } else {
            value = Secure.getInt(resolver, "package_verifier_enable", 1);
        }
        if (value != 0) {
            return true;
        }
        return false;
    }
}
