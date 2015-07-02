package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class DeleteResourceRequest implements SafeParcelable {
    public static final Creator<DeleteResourceRequest> CREATOR;
    final DriveId Zc;
    final int mVersionCode;

    static {
        CREATOR = new n();
    }

    DeleteResourceRequest(int versionCode, DriveId id) {
        this.mVersionCode = versionCode;
        this.Zc = id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
