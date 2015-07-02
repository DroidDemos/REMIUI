package com.google.android.finsky.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.download.DownloadReceiver;
import com.google.android.finsky.installer.PackageInstallerFacade;
import com.google.android.finsky.installer.PackageInstallerFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Notifier;

public class RemoveAssetReceiver extends DownloadReceiver {
    private static Notifier sNotificationHelper;

    public static void initialize(Notifier notificationHelper) {
        sNotificationHelper = notificationHelper;
    }

    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
            setResultCode(-1);
            if (intent.getStringExtra("from").equals("google.com") && intent.getCategories().contains("REMOVE_ASSET")) {
                FinskyApp.get().getAppStates().load(new Runnable() {
                    public void run() {
                        RemoveAssetReceiver.this.finishOnReceive(context, intent);
                    }
                });
            }
        }
    }

    private void finishOnReceive(Context context, Intent intent) {
        String packageName = intent.getStringExtra("asset_package");
        String assetName = intent.getStringExtra("asset_name");
        boolean isMalicious = Boolean.parseBoolean(intent.getStringExtra("asset_malicious"));
        if (TextUtils.isEmpty(packageName)) {
            FinskyLog.w("Unexpected empty package name", new Object[0]);
            return;
        }
        AppState appState = FinskyApp.get().getAppStates().getApp(packageName);
        removePackage(context, packageName, isMalicious);
        if (appState != null) {
            notifyRemovingKnownApp(packageName, assetName, isMalicious, appState);
        }
    }

    private void notifyRemovingKnownApp(String packageName, String assetName, boolean malicious, AppState appState) {
        if (appState.packageManagerState == null) {
            return;
        }
        if (malicious) {
            sNotificationHelper.showMaliciousAssetRemovedMessage(assetName, packageName);
        } else {
            sNotificationHelper.showNormalAssetRemovedMessage(assetName, packageName);
        }
    }

    private void removePackage(Context context, String packageName, boolean isMalicious) {
        FinskyLog.d("Removing package '%s'. Malicious='%s'", packageName, Boolean.valueOf(isMalicious));
        PackageInstallerFacade packageInstaller = PackageInstallerFactory.getPackageInstaller();
        if (isMalicious) {
            PackageManager packageManager = context.getPackageManager();
            try {
                for (String uidPackageName : packageManager.getPackagesForUid(packageManager.getPackageInfo(packageName, 0).applicationInfo.uid)) {
                    FinskyLog.d("Removing package '%s' (child of '%s')", uidPackageName, packageName);
                    packageInstaller.uninstallPackage(uidPackageName);
                }
                return;
            } catch (NameNotFoundException e) {
                FinskyLog.d("'%s' not found in PM.", packageName);
                return;
            }
        }
        packageInstaller.uninstallPackage(packageName);
    }
}
