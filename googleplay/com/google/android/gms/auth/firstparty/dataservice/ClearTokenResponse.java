package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ClearTokenResponse implements SafeParcelable {
    public static final q CREATOR;
    final String Gv;
    final int version;

    static {
        CREATOR = new q();
    }

    ClearTokenResponse(int version, String statusWireCode) {
        this.version = version;
        this.Gv = statusWireCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        q.a(this, dest, flags);
    }
}
