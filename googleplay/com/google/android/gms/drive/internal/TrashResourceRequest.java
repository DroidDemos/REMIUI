package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class TrashResourceRequest implements SafeParcelable {
    public static final Creator<TrashResourceRequest> CREATOR;
    final DriveId Zc;
    final int mVersionCode;

    static {
        CREATOR = new bh();
    }

    TrashResourceRequest(int versionCode, DriveId id) {
        this.mVersionCode = versionCode;
        this.Zc = id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        bh.a(this, dest, flags);
    }
}
