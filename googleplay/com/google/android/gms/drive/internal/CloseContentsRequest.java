package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

public class CloseContentsRequest implements SafeParcelable {
    public static final Creator<CloseContentsRequest> CREATOR;
    final Contents Ze;
    final Boolean Zg;
    final int mVersionCode;

    static {
        CREATOR = new g();
    }

    CloseContentsRequest(int versionCode, Contents contentsReference, Boolean saveResults) {
        this.mVersionCode = versionCode;
        this.Ze = contentsReference;
        this.Zg = saveResults;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        g.a(this, dest, flags);
    }
}
