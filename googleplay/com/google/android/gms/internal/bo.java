package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@fd
public final class bo implements SafeParcelable {
    public static final bp CREATOR;
    public final int backgroundColor;
    public final int oS;
    public final int oT;
    public final int oU;
    public final int oV;
    public final int oW;
    public final int oX;
    public final int oY;
    public final String oZ;
    public final int pa;
    public final String pb;
    public final int pc;
    public final int pd;
    public final String query;
    public final int versionCode;

    static {
        CREATOR = new bp();
    }

    bo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str, int i10, String str2, int i11, int i12, String str3) {
        this.versionCode = i;
        this.oS = i2;
        this.backgroundColor = i3;
        this.oT = i4;
        this.oU = i5;
        this.oV = i6;
        this.oW = i7;
        this.oX = i8;
        this.oY = i9;
        this.oZ = str;
        this.pa = i10;
        this.pb = str2;
        this.pc = i11;
        this.pd = i12;
        this.query = str3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        bp.a(this, out, flags);
    }
}
