package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class qp implements SafeParcelable {
    public static final qq CREATOR;
    private final String Fl;
    private final String aBg;
    private final int mVersionCode;

    static {
        CREATOR = new qq();
    }

    qp(int i, String str, String str2) {
        this.mVersionCode = i;
        this.Fl = str;
        this.aBg = str2;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.Fl;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String qD() {
        return this.aBg;
    }

    public void writeToParcel(Parcel out, int flags) {
        qq.a(this, out, flags);
    }
}
