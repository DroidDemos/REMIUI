package com.google.android.finsky.download.obb;

import android.os.Build.VERSION;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import java.io.File;

public class ObbPackageTracker implements PackageStatusListener {
    private final int GINGERBREAD_MR1;

    public ObbPackageTracker() {
        this.GINGERBREAD_MR1 = 10;
    }

    public void onPackageAdded(String packageName) {
    }

    public void onPackageChanged(String packageName) {
    }

    public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
    }

    public void onPackageRemoved(String packageName, boolean replacing) {
        if (VERSION.SDK_INT <= 10 && !replacing) {
            File parentDir = ObbFactory.getParentDirectory(packageName);
            if (parentDir.exists()) {
                for (File obbFile : parentDir.listFiles()) {
                    obbFile.delete();
                }
                parentDir.delete();
            }
        }
    }

    public void onPackageFirstLaunch(String packageName) {
    }
}
