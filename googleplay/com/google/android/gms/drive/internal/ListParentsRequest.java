package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class ListParentsRequest implements SafeParcelable {
    public static final Creator<ListParentsRequest> CREATOR;
    final DriveId aaO;
    final int mVersionCode;

    static {
        CREATOR = new ai();
    }

    ListParentsRequest(int versionCode, DriveId driveId) {
        this.mVersionCode = versionCode;
        this.aaO = driveId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ai.a(this, dest, flags);
    }
}
