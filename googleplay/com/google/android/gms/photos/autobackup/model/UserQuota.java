package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UserQuota implements SafeParcelable {
    public static final Creator<UserQuota> CREATOR;
    private long aGT;
    private long aGU;
    private boolean aGV;
    private boolean aGW;
    public final int mVersionCode;

    static {
        CREATOR = new f();
    }

    public UserQuota() {
        this.mVersionCode = 1;
    }

    public UserQuota(int versionCode, long quotaLimit, long quotaUsed, boolean quotaUnlimited, boolean disableFullResUploads) {
        this.mVersionCode = versionCode;
        this.aGT = quotaLimit;
        this.aGU = quotaUsed;
        this.aGV = quotaUnlimited;
        this.aGW = disableFullResUploads;
    }

    public boolean areFullResUploadsDisabled() {
        return this.aGW;
    }

    public int describeContents() {
        return 0;
    }

    public long getQuotaLimit() {
        return this.aGT;
    }

    public long getQuotaUsed() {
        return this.aGU;
    }

    public boolean isQuotaUnlimited() {
        return this.aGV;
    }

    public void writeToParcel(Parcel out, int flags) {
        f.a(this, out, flags);
    }
}
