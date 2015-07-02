package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;

@fd
public final class fo implements SafeParcelable {
    public static final fp CREATOR;
    public final int errorCode;
    public final int orientation;
    public final long qB;
    public final List<String> qx;
    public final List<String> qy;
    public final String sg;
    public final boolean tT;
    public final String tU;
    public final long tV;
    public final boolean tW;
    public final long tX;
    public final List<String> tY;
    public final String tZ;
    public final long ua;
    public final String ub;
    public final boolean uc;
    public final String ud;
    public final String ue;
    public final boolean uf;
    public final boolean ug;
    public final boolean uh;
    public final int versionCode;

    static {
        CREATOR = new fp();
    }

    fo(int i, String str, String str2, List<String> list, int i2, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i3, String str3, long j4, String str4, boolean z2, String str5, String str6, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.versionCode = i;
        this.sg = str;
        this.tU = str2;
        this.qx = list != null ? Collections.unmodifiableList(list) : null;
        this.errorCode = i2;
        this.qy = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.tV = j;
        this.tW = z;
        this.tX = j2;
        this.tY = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.qB = j3;
        this.orientation = i3;
        this.tZ = str3;
        this.ua = j4;
        this.ub = str4;
        this.uc = z2;
        this.ud = str5;
        this.ue = str6;
        this.uf = z3;
        this.ug = z4;
        this.tT = z5;
        this.uh = z6;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        fp.a(this, out, flags);
    }
}
