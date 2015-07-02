package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RequestIndexingSpecification implements SafeParcelable {
    public static final ab CREATOR;
    final int mVersionCode;

    static {
        CREATOR = new ab();
    }

    RequestIndexingSpecification(int versionCode) {
        this.mVersionCode = versionCode;
    }

    public int describeContents() {
        ab abVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ab abVar = CREATOR;
        ab.a(this, out, flags);
    }
}
