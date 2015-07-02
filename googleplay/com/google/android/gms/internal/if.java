package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class if implements SafeParcelable {
    public static final ig CREATOR;
    final Bundle Gp;
    final byte[] Gq;
    final int responseCode;
    final int versionCode;

    static {
        CREATOR = new ig();
    }

    public if(int i, int i2, Bundle bundle, byte[] bArr) {
        this.versionCode = i;
        this.responseCode = i2;
        this.Gp = bundle;
        this.Gq = bArr;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        ig.a(this, parcel, flags);
    }
}
