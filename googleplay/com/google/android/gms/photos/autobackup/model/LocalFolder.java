package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LocalFolder implements SafeParcelable {
    public static final Creator<LocalFolder> CREATOR;
    private String aGO;
    private String aGP;
    private boolean aGs;
    public final int mVersionCode;

    static {
        CREATOR = new c();
    }

    LocalFolder(int versionCode, String folderName, String bucketId, boolean enabled) {
        this.mVersionCode = versionCode;
        this.aGO = folderName;
        this.aGP = bucketId;
        this.aGs = enabled;
    }

    public int describeContents() {
        return 0;
    }

    public String getBucketId() {
        return this.aGP;
    }

    public String getFolderName() {
        return this.aGO;
    }

    public boolean isAutoBackupEnabled() {
        return this.aGs;
    }

    public String toString() {
        return this.aGO + " (enabled=" + this.aGs + ")";
    }

    public void writeToParcel(Parcel parcel, int flags) {
        c.a(this, parcel, flags);
    }
}
