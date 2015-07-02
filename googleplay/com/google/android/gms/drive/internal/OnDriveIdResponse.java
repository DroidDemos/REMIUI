package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OnDriveIdResponse implements SafeParcelable {
    public static final Creator<OnDriveIdResponse> CREATOR;
    DriveId Zc;
    final int mVersionCode;

    static {
        CREATOR = new ao();
    }

    OnDriveIdResponse(int versionCode, DriveId driveId) {
        this.mVersionCode = versionCode;
        this.Zc = driveId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ao.a(this, dest, flags);
    }
}
