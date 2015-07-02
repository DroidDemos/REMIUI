package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Country implements SafeParcelable {
    public static final a CREATOR;
    public String mCode;
    public String mName;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    public Country() {
        this.mVersionCode = 1;
    }

    Country(int versionCode, String name, String code) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.mCode = code;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
