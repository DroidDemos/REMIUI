package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.g;

public class OnListParentsResponse extends g implements SafeParcelable {
    public static final Creator<OnListParentsResponse> CREATOR;
    final DataHolder aba;
    final int mVersionCode;

    static {
        CREATOR = new as();
    }

    OnListParentsResponse(int versionCode, DataHolder parents) {
        this.mVersionCode = versionCode;
        this.aba = parents;
    }

    protected void K(Parcel parcel, int i) {
        as.a(this, parcel, i);
    }

    public int describeContents() {
        return 0;
    }
}
