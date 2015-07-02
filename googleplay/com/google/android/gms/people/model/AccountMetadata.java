package com.google.android.gms.people.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountMetadata implements SafeParcelable {
    public static final a CREATOR;
    public boolean isPagePeriodicSyncEnabled;
    public boolean isPageTickleSyncEnabled;
    public boolean isPlusEnabled;
    public boolean isSyncEnabled;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    public AccountMetadata() {
        this.mVersionCode = 2;
    }

    AccountMetadata(int versionCode, boolean isPlusEnabled, boolean isSyncEnabled, boolean isPagePeriodicSyncEnabled, boolean isPageTickleSyncEnabled) {
        this.mVersionCode = versionCode;
        this.isPlusEnabled = isPlusEnabled;
        this.isSyncEnabled = isSyncEnabled;
        this.isPagePeriodicSyncEnabled = isPagePeriodicSyncEnabled;
        this.isPageTickleSyncEnabled = isPageTickleSyncEnabled;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
