package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class LoadRealtimeRequest implements SafeParcelable {
    public static final Creator<LoadRealtimeRequest> CREATOR;
    final DriveId Yb;
    final boolean aaP;
    final int mVersionCode;

    static {
        CREATOR = new aj();
    }

    LoadRealtimeRequest(int versionCode, DriveId driveId, boolean useTestMode) {
        this.mVersionCode = versionCode;
        this.Yb = driveId;
        this.aaP = useTestMode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aj.a(this, dest, flags);
    }
}
