package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ClearTokenRequest implements SafeParcelable {
    public static final p CREATOR;
    String GP;
    final int version;

    static {
        CREATOR = new p();
    }

    public ClearTokenRequest() {
        this.version = 1;
    }

    ClearTokenRequest(int version, String token) {
        this.version = version;
        this.GP = token;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        p.a(this, dest, flags);
    }
}
