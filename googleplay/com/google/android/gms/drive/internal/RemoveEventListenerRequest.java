package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class RemoveEventListenerRequest implements SafeParcelable {
    public static final Creator<RemoveEventListenerRequest> CREATOR;
    final int YZ;
    final DriveId Yb;
    final int mVersionCode;

    static {
        CREATOR = new bc();
    }

    RemoveEventListenerRequest(int versionCode, DriveId driveId, int eventType) {
        this.mVersionCode = versionCode;
        this.Yb = driveId;
        this.YZ = eventType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        bc.a(this, dest, flags);
    }
}
