package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RegisteredPackageInfo implements SafeParcelable {
    public static final aa CREATOR;
    public final boolean blocked;
    final int mVersionCode;
    public final String packageName;
    public final long reclaimableDiskBytes;
    public final long usedDiskBytes;

    static {
        CREATOR = new aa();
    }

    RegisteredPackageInfo(int versionCode, String packageName, long usedDiskBytes, boolean blocked, long reclaimableDiskBytes) {
        this.mVersionCode = versionCode;
        this.packageName = packageName;
        this.usedDiskBytes = usedDiskBytes;
        this.blocked = blocked;
        this.reclaimableDiskBytes = reclaimableDiskBytes;
    }

    public int describeContents() {
        aa aaVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        aa aaVar = CREATOR;
        aa.a(this, out, flags);
    }
}
