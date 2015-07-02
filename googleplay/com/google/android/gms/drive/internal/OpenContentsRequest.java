package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OpenContentsRequest implements SafeParcelable {
    public static final Creator<OpenContentsRequest> CREATOR;
    final int Ya;
    final DriveId Zc;
    final int abb;
    final int mVersionCode;

    static {
        CREATOR = new ay();
    }

    OpenContentsRequest(int versionCode, DriveId id, int mode, int baseRequestId) {
        this.mVersionCode = versionCode;
        this.Zc = id;
        this.Ya = mode;
        this.abb = baseRequestId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ay.a(this, dest, flags);
    }
}
