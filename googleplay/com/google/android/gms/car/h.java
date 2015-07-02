package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class h implements SafeParcelable {
    public static final Creator<h> CREATOR;
    public int Le;
    public int Lf;
    public int Lg;
    public int Lh;
    final int mVersionCode;
    public int type;

    static {
        CREATOR = new i();
    }

    public h() {
        this.mVersionCode = 1;
    }

    public h(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mVersionCode = i;
        this.Le = i2;
        this.type = i3;
        this.Lf = i4;
        this.Lg = i5;
        this.Lh = i6;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
