package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class AuthorizeAccessRequest implements SafeParcelable {
    public static final Creator<AuthorizeAccessRequest> CREATOR;
    final DriveId Yb;
    final long Za;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    AuthorizeAccessRequest(int versionCode, long appId, DriveId driveId) {
        this.mVersionCode = versionCode;
        this.Za = appId;
        this.Yb = driveId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
