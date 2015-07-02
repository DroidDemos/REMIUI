package com.google.android.vending.verifier;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public class PackageVerificationActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVisible(false);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!"android.intent.action.VIEW".equals(action) && !"android.intent.action.INSTALL_PACKAGE".equals(action)) {
            FinskyLog.w("Unexpected action %s", action);
        } else if (checkBackportPrerequisites()) {
            FinskyLog.w("Verification requested through action=%s", action);
            intent.putExtra("com.google.android.vending.verifier.extra.FROM_VERIFICATION_ACTIVITY", true);
            PackageVerificationService.start(this, intent);
        } else {
            sendToPackageInstaller(intent);
        }
        finish();
    }

    void sendToPackageInstaller(Intent intent) {
        intent.setPackage("com.android.packageinstaller");
        intent.setComponent(new ComponentName("com.android.packageinstaller", "com.android.packageinstaller.PackageInstallerActivity"));
        startActivity(intent);
    }

    private boolean checkBackportPrerequisites() {
        if (VERSION.SDK_INT >= 17) {
            FinskyLog.wtf("Skipping verification. Unexpected SDK=%d", Integer.valueOf(VERSION.SDK_INT));
            return false;
        } else if (Secure.getInt(getContentResolver(), "package_verifier_enable", 1) != 1) {
            FinskyLog.d("Skipping verification. Disabled by user setting", new Object[0]);
            return false;
        } else if (!((Boolean) G.antiMalwareActivityEnabled.get()).booleanValue()) {
            FinskyLog.d("Skipping verification. Disabled by Gservices", new Object[0]);
            return false;
        } else if (FinskyApp.get().getInstallPolicies().hasNetwork()) {
            return true;
        } else {
            FinskyLog.d("Skipping verification. Network inactive", new Object[0]);
            return false;
        }
    }
}
