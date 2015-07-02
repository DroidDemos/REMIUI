package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class as implements SafeParcelable {
    public static final Creator<as> CREATOR;
    public final int aXg;
    public final int statusCode;
    public final int versionCode;

    static {
        CREATOR = new at();
    }

    as(int i, int i2, int i3) {
        this.versionCode = i;
        this.statusCode = i2;
        this.aXg = i3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        at.a(this, dest, flags);
    }
}
