package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class p implements SafeParcelable {
    public static final Creator<p> CREATOR;
    public final int aWN;
    public final int statusCode;
    public final int versionCode;

    static {
        CREATOR = new q();
    }

    p(int i, int i2, int i3) {
        this.versionCode = i;
        this.statusCode = i2;
        this.aWN = i3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        q.a(this, dest, flags);
    }
}
