package com.google.android.vending.verifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public class PackageVerificationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_NEEDS_VERIFICATION".equals(action) || "android.intent.action.PACKAGE_VERIFIED".equals(action)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                int id = extras.getInt("android.content.pm.extra.VERIFICATION_ID");
                if (checkPrerequisites(extras)) {
                    FinskyLog.d("Verification requested, id = %d", Integer.valueOf(id));
                    PackageVerificationService.start(context, intent);
                    return;
                }
                reportVerificationOk(context, id);
                return;
            }
            return;
        }
        FinskyLog.w("Unexpected action %s", action);
    }

    private void reportVerificationOk(Context context, int id) {
        context.getPackageManager().verifyPendingInstall(id, 1);
    }

    private boolean checkPrerequisites(Bundle extras) {
        if (!((Boolean) G.platformAntiMalwareEnabled.get()).booleanValue()) {
            FinskyLog.d("Skipping verification because disabled", new Object[0]);
            return false;
        } else if (!FinskyApp.get().getInstallPolicies().hasNetwork()) {
            FinskyLog.d("Skipping verification because network inactive", new Object[0]);
            return false;
        } else if (VERSION.SDK_INT < 17) {
            FinskyLog.d("Skipping verification because SDK=%d", Integer.valueOf(VERSION.SDK_INT));
            return false;
        } else if (extras.getInt("android.content.pm.extra.VERIFICATION_INSTALLER_UID") != Process.myUid()) {
            return true;
        } else {
            FinskyLog.d("Skipping verification because own installation", new Object[0]);
            return false;
        }
    }
}
