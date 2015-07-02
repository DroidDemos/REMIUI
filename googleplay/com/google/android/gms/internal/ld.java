package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ld implements SafeParcelable {
    public static final le CREATOR;
    public final String WP;
    public final int WQ;
    final int mVersionCode;

    static {
        CREATOR = new le();
    }

    public ld(int i, String str, int i2) {
        this.mVersionCode = i;
        this.WP = str;
        this.WQ = i2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        le.a(this, out, flags);
    }
}
