package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AutoBackupSettings implements SafeParcelable {
    public static final Creator<AutoBackupSettings> CREATOR;
    private String Fl;
    private boolean aGA;
    private boolean aGB;
    private boolean aGC;
    private boolean aGD;
    private boolean aGE;
    private UserQuota aGF;
    private boolean aGs;
    private boolean aGy;
    private boolean aGz;
    public final int mVersionCode;

    static {
        CREATOR = new a();
    }

    public AutoBackupSettings() {
        this.mVersionCode = 1;
    }

    public AutoBackupSettings(int versionCode, String accountName, boolean isEnabled, boolean wifiOnly, boolean roamingUpload, boolean chargingOnly, boolean wifiOnlyVideo, boolean uploadFullResolution, boolean localFoldersAutoBackup, boolean photosStorageManaged, UserQuota userQuota) {
        this.mVersionCode = versionCode;
        this.Fl = accountName;
        this.aGs = isEnabled;
        this.aGy = wifiOnly;
        this.aGz = roamingUpload;
        this.aGA = chargingOnly;
        this.aGB = wifiOnlyVideo;
        this.aGC = uploadFullResolution;
        this.aGD = localFoldersAutoBackup;
        this.aGE = photosStorageManaged;
        this.aGF = userQuota;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.Fl;
    }

    public UserQuota getUserQuota() {
        return this.aGF;
    }

    public boolean isChargingOnly() {
        return this.aGA;
    }

    public boolean isEnabled() {
        return this.aGs;
    }

    public boolean isLocalFoldersAutoBackup() {
        return this.aGD;
    }

    public boolean isPhotosStorageManaged() {
        return this.aGE;
    }

    public boolean isRoamingUpload() {
        return this.aGz;
    }

    public boolean isUploadFullResolution() {
        return this.aGC;
    }

    public boolean isWifiOnly() {
        return this.aGy;
    }

    public boolean isWifiOnlyVideo() {
        return this.aGB;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
