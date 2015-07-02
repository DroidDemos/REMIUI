package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@fd
public final class gx implements SafeParcelable {
    public static final gy CREATOR;
    public final int versionCode;
    public String wR;
    public int wS;
    public int wT;
    public boolean wU;

    static {
        CREATOR = new gy();
    }

    gx(int i, String str, int i2, int i3, boolean z) {
        this.versionCode = i;
        this.wR = str;
        this.wS = i2;
        this.wT = i3;
        this.wU = z;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        gy.a(this, out, flags);
    }
}
