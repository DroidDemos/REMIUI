package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ac implements SafeParcelable {
    public static final ad CREATOR;
    final DocumentId[] Ed;
    final int Ee;
    final int mVersionCode;
    final String xM;

    static {
        CREATOR = new ad();
    }

    ac(int i, String str, DocumentId[] documentIdArr, int i2) {
        this.mVersionCode = i;
        this.xM = str;
        this.Ed = documentIdArr;
        this.Ee = i2;
    }

    public int describeContents() {
        ad adVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ad adVar = CREATOR;
        ad.a(this, dest, flags);
    }
}
