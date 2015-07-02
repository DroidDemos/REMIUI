package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

public class OnContentsResponse implements SafeParcelable {
    public static final Creator<OnContentsResponse> CREATOR;
    final Contents ZL;
    final boolean aaT;
    final int mVersionCode;

    static {
        CREATOR = new al();
    }

    OnContentsResponse(int versionCode, Contents contents, boolean outOfDate) {
        this.mVersionCode = versionCode;
        this.ZL = contents;
        this.aaT = outOfDate;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        al.a(this, dest, flags);
    }
}
