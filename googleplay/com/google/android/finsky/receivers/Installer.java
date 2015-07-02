package com.google.android.finsky.receivers;

import android.graphics.Bitmap;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener;
import java.util.List;

public interface Installer {

    public static class InstallerProgressReport {
        public final long bytesCompleted;
        public final long bytesTotal;
        public final int downloadStatus;
        public final InstallerState installerState;

        public InstallerProgressReport(InstallerState installerState, long bytesCompleted, long bytesTotal, int downloadStatus) {
            this.installerState = installerState;
            this.bytesCompleted = bytesCompleted;
            this.bytesTotal = bytesTotal;
            this.downloadStatus = downloadStatus;
        }
    }

    public enum InstallerState {
        NOT_TRACKED,
        DOWNLOAD_PENDING,
        DOWNLOADING,
        INSTALLING,
        UNINSTALLING;

        public boolean isDownloadingOrInstalling() {
            return this == DOWNLOAD_PENDING || this == DOWNLOADING || this == INSTALLING;
        }
    }

    void addListener(InstallerListener installerListener);

    void cancel(String str);

    void cancelAll();

    InstallerProgressReport getProgress(String str);

    InstallerState getState(String str);

    boolean isBusy();

    void promiseInstall(String str, long j, String str2, Bitmap bitmap);

    void removeListener(InstallerListener installerListener);

    void requestInstall(String str, int i, String str2, String str3, boolean z, String str4, int i2);

    void setDeliveryToken(String str, String str2);

    void setEarlyUpdate(String str);

    void setMobileDataAllowed(String str);

    void setVisibility(String str, boolean z, boolean z2, boolean z3);

    void start(Runnable runnable);

    void startDeferredInstalls();

    void uninstallAssetSilently(String str);

    void uninstallPackagesByUid(String str);

    void updateInstalledApps(List<Document> list, String str, boolean z, boolean z2, boolean z3, int i);

    void updateSingleInstalledApp(String str, int i, String str2, String str3, boolean z, int i2);
}
