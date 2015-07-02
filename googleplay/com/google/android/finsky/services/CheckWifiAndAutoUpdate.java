package com.google.android.finsky.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.UpdateChecker;
import com.google.android.finsky.gearhead.GearheadStateMonitor;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.utils.FinskyLog;

public class CheckWifiAndAutoUpdate extends Service {
    public int onStartCommand(Intent intent, int flags, int startId) {
        InstallPolicies installPolicies = FinskyApp.get().getInstallPolicies();
        if (!installPolicies.isWifiNetwork() || installPolicies.isMobileHotspot()) {
            FinskyLog.d("Checking wifi: disabled, will check wifi again later.", new Object[0]);
            UpdateChecker.logWifiAutoUpdate(false, "wifi_checker", false);
            CheckPreconditionsAndAutoUpdateScheduler.scheduleCheck(1);
            stopSelf();
        } else {
            FinskyLog.d("Checking wifi: enabled, proceeding with auto-update.", new Object[0]);
            loadLibrariesAndAutoUpdate();
        }
        return 2;
    }

    private void loadLibrariesAndAutoUpdate() {
        Runnable continueRunnable = new Runnable() {
            private int mLoaded;

            public void run() {
                this.mLoaded++;
                if (this.mLoaded == 3) {
                    CheckWifiAndAutoUpdate.this.onLoaded();
                }
            }
        };
        FinskyApp.get().getLibraries().load(continueRunnable);
        FinskyApp.get().getAppStates().load(continueRunnable);
        GearheadStateMonitor.initialize(continueRunnable);
    }

    private void onLoaded() {
        new UpdateChecker(this, FinskyApp.get().getLibraries(), FinskyApp.get().getAppStates(), FinskyApp.get().getInstallPolicies(), FinskyApp.get().getInstaller(), FinskyApp.get().getNotifier()).checkForUpdates(new Runnable() {
            public void run() {
                CheckWifiAndAutoUpdate.this.stopSelf();
            }
        }, new Runnable() {
            public void run() {
                CheckWifiAndAutoUpdate.this.stopSelf();
            }
        }, "wifi_checker", false);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
