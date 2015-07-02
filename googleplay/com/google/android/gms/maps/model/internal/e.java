package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class e implements SafeParcelable {
    public static final f CREATOR;
    private a aAR;
    private final int mVersionCode;

    static {
        CREATOR = new f();
    }

    public e() {
        this.mVersionCode = 1;
    }

    e(int i, a aVar) {
        this.mVersionCode = i;
        this.aAR = aVar;
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public a qx() {
        return this.aAR;
    }

    public void writeToParcel(Parcel out, int flags) {
        f.a(this, out, flags);
    }
}
