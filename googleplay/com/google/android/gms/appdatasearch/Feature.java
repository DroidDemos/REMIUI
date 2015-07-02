package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Feature implements SafeParcelable {
    public static final h CREATOR;
    final Bundle Db;
    public final int id;
    final int mVersionCode;

    static {
        CREATOR = new h();
    }

    Feature(int versionCode, int id, Bundle parameters) {
        this.mVersionCode = versionCode;
        this.id = id;
        this.Db = parameters;
    }

    public int describeContents() {
        h hVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        h hVar = CREATOR;
        h.a(this, dest, flags);
    }
}
