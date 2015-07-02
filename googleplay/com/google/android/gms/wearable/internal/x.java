package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class x implements SafeParcelable {
    public static final Creator<x> CREATOR;
    public final m aWR;
    public final int statusCode;
    public final int versionCode;

    static {
        CREATOR = new y();
    }

    x(int i, int i2, m mVar) {
        this.versionCode = i;
        this.statusCode = i2;
        this.aWR = mVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        y.a(this, dest, flags);
    }
}
