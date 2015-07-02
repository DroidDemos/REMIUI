package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class k implements SafeParcelable {
    public static final l CREATOR;
    final String corpusName;
    final Feature[] features;
    final int mVersionCode;

    static {
        CREATOR = new l();
    }

    k(int i, String str, Feature[] featureArr) {
        this.mVersionCode = i;
        this.corpusName = str;
        this.features = featureArr;
    }

    public int describeContents() {
        l lVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        l lVar = CREATOR;
        l.a(this, out, flags);
    }
}
