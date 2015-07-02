package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@fd
public final class ba implements SafeParcelable {
    public static final bb CREATOR;
    public final Bundle extras;
    public final long of;
    public final int og;
    public final List<String> oh;
    public final boolean oi;
    public final int oj;
    public final boolean ok;
    public final String ol;
    public final bo om;
    public final Location on;
    public final String oo;
    public final Bundle op;
    public final int versionCode;

    static {
        CREATOR = new bb();
    }

    public ba(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, bo boVar, Location location, String str2, Bundle bundle2) {
        this.versionCode = i;
        this.of = j;
        this.extras = bundle;
        this.og = i2;
        this.oh = list;
        this.oi = z;
        this.oj = i3;
        this.ok = z2;
        this.ol = str;
        this.om = boVar;
        this.on = location;
        this.oo = str2;
        this.op = bundle2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        bb.a(this, out, flags);
    }
}
