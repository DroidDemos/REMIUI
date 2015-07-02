package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class p implements SafeParcelable {
    public static final q CREATOR;
    private a aAS;
    private final int mVersionCode;

    static {
        CREATOR = new q();
    }

    public p() {
        this.mVersionCode = 1;
    }

    p(int i, a aVar) {
        this.mVersionCode = i;
        this.aAS = aVar;
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public a qz() {
        return this.aAS;
    }

    public void writeToParcel(Parcel out, int flags) {
        q.a(this, out, flags);
    }
}
