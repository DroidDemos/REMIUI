package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.StorageStats;

public class OnStorageStatsResponse implements SafeParcelable {
    public static final Creator<OnStorageStatsResponse> CREATOR;
    StorageStats aaF;
    final int mVersionCode;

    static {
        CREATOR = new aw();
    }

    OnStorageStatsResponse(int versionCode, StorageStats storageStats) {
        this.mVersionCode = versionCode;
        this.aaF = storageStats;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aw.a(this, dest, flags);
    }
}
