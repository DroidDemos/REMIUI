package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CheckRealNameResponse implements SafeParcelable {
    public static final o CREATOR;
    String Gv;
    final int version;

    static {
        CREATOR = new o();
    }

    CheckRealNameResponse(int version, String statusWireCode) {
        this.version = version;
        this.Gv = statusWireCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        o.a(this, dest, flags);
    }
}
