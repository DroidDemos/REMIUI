package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Section implements SafeParcelable {
    public static final af CREATOR;
    final int mVersionCode;
    public final String name;
    public final int snippetLength;
    public final boolean snippeted;

    static {
        CREATOR = new af();
    }

    Section(int versionCode, String name, boolean snippeted, int snippetLength) {
        this.mVersionCode = versionCode;
        this.name = name;
        this.snippeted = snippeted;
        this.snippetLength = snippetLength;
    }

    public int describeContents() {
        af afVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        af afVar = CREATOR;
        af.a(this, out, flags);
    }
}
