package com.google.android.gms.location.copresence.debug;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class b implements SafeParcelable {
    public static final c CREATOR;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public b() {
        this(1);
    }

    b(int i) {
        this.mVersionCode = i;
    }

    public int describeContents() {
        c cVar = CREATOR;
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
