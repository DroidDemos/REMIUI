package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StorageStats implements SafeParcelable {
    public static final ag CREATOR;
    public final long allServicesDiskUsageBytes;
    final int mVersionCode;
    public final long otherReclaimableBytes;
    public final RegisteredPackageInfo[] packageStats;
    public final long searchDiskUsageBytes;

    static {
        CREATOR = new ag();
    }

    StorageStats(int versionCode, RegisteredPackageInfo[] packageStats, long otherReclaimableBytes, long searchDiskUsageBytes, long allServicesDiskUsageBytes) {
        this.mVersionCode = versionCode;
        this.packageStats = packageStats;
        this.otherReclaimableBytes = otherReclaimableBytes;
        this.searchDiskUsageBytes = searchDiskUsageBytes;
        this.allServicesDiskUsageBytes = allServicesDiskUsageBytes;
    }

    public int describeContents() {
        ag agVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ag agVar = CREATOR;
        ag.a(this, out, flags);
    }
}
