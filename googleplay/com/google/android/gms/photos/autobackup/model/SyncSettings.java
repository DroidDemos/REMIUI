package com.google.android.gms.photos.autobackup.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SyncSettings implements SafeParcelable {
    public static final Creator<SyncSettings> CREATOR;
    private boolean aGR;
    private boolean aGS;
    public final int mVersionCode;

    static {
        CREATOR = new e();
    }

    public SyncSettings(int versionCode, boolean masterSyncEnabled, boolean accountSyncEnabled) {
        this.mVersionCode = versionCode;
        this.aGR = masterSyncEnabled;
        this.aGS = accountSyncEnabled;
    }

    public int describeContents() {
        return 0;
    }

    public boolean isAccountSyncEnabled() {
        return this.aGS;
    }

    public boolean isMasterSyncEnabled() {
        return this.aGR;
    }

    public void writeToParcel(Parcel out, int flags) {
        e.a(this, out, flags);
    }
}
