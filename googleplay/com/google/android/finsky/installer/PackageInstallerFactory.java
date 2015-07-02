package com.google.android.finsky.installer;

import android.content.Context;
import android.os.Build.VERSION;

public class PackageInstallerFactory {
    private static PackageInstallerFacade sPackageInstaller;

    public static void init(Context context) {
        if (VERSION.SDK_INT >= 21) {
            sPackageInstaller = new PackageInstallerImpl(context);
        } else {
            sPackageInstaller = new PackageInstallerLegacyImpl(context);
        }
    }

    public static PackageInstallerFacade getPackageInstaller() {
        return sPackageInstaller;
    }
}
