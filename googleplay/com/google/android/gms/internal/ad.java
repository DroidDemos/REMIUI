package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@fd
public final class ad implements SafeParcelable {
    public static final ae CREATOR;
    public final boolean ml;
    public final boolean mv;
    public final int versionCode;

    static {
        CREATOR = new ae();
    }

    ad(int i, boolean z, boolean z2) {
        this.versionCode = i;
        this.ml = z;
        this.mv = z2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ae.a(this, out, flags);
    }
}
