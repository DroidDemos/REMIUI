package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StorageStats implements SafeParcelable {
    public static final Creator<StorageStats> CREATOR;
    final long YA;
    final long YB;
    final long YC;
    final long YD;
    final int YE;
    final int mVersionCode;

    static {
        CREATOR = new e();
    }

    StorageStats(int versionCode, long metadataSizeBytes, long cachedContentsSizeBytes, long pinnedItemsSizeBytes, long totalSizeBytes, int numPinnedItems) {
        this.mVersionCode = versionCode;
        this.YA = metadataSizeBytes;
        this.YB = cachedContentsSizeBytes;
        this.YC = pinnedItemsSizeBytes;
        this.YD = totalSizeBytes;
        this.YE = numPinnedItems;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        e.a(this, out, flags);
    }
}
