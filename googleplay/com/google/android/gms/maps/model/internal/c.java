package com.google.android.gms.maps.model.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class c implements SafeParcelable {
    public static final d CREATOR;
    private Bundle aAP;
    private final int mVersionCode;
    private int type;

    static {
        CREATOR = new d();
    }

    c(int i, int i2, Bundle bundle) {
        this.mVersionCode = i;
        this.type = i2;
        this.aAP = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public int getType() {
        return this.type;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public Bundle qw() {
        return this.aAP;
    }

    public void writeToParcel(Parcel out, int flags) {
        d.a(this, out, flags);
    }
}
