package com.google.android.finsky.installer;

public interface InstallerListener {

    public enum InstallerPackageEvent {
        DOWNLOAD_PENDING,
        DOWNLOADING,
        DOWNLOAD_CANCELLED,
        DOWNLOAD_ERROR,
        INSTALLING,
        INSTALL_ERROR,
        INSTALLED,
        UNINSTALLING,
        UNINSTALLED,
        UNINSTALL_ERROR
    }

    void onInstallPackageEvent(String str, InstallerPackageEvent installerPackageEvent, int i);
}
