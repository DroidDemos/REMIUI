package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class CancelPendingActionsRequest implements SafeParcelable {
    public static final Creator<CancelPendingActionsRequest> CREATOR;
    final List<String> YO;
    final int mVersionCode;

    static {
        CREATOR = new d();
    }

    CancelPendingActionsRequest(int versionCode, List<String> trackingTags) {
        this.mVersionCode = versionCode;
        this.YO = trackingTags;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
