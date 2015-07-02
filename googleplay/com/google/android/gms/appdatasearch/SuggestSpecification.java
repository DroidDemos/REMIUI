package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SuggestSpecification implements SafeParcelable {
    public static final ah CREATOR;
    final int mVersionCode;

    static {
        CREATOR = new ah();
    }

    public SuggestSpecification() {
        this(2);
    }

    SuggestSpecification(int versionCode) {
        this.mVersionCode = versionCode;
    }

    public int describeContents() {
        ah ahVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ah ahVar = CREATOR;
        ah.a(this, out, flags);
    }
}
