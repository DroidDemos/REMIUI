package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class StorageInfoResponse implements SafeParcelable {
    public static final Creator<StorageInfoResponse> CREATOR;
    public final List<PackageStorageInfo> packageStorageInfo;
    public final int statusCode;
    public final long totalSizeBytes;
    public final int versionCode;

    static {
        CREATOR = new av();
    }

    StorageInfoResponse(int versionCode, int statusCode, long totalSizeBytes, List<PackageStorageInfo> packageStorageInfo) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.totalSizeBytes = totalSizeBytes;
        this.packageStorageInfo = packageStorageInfo;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        av.a(this, out, flags);
    }
}
