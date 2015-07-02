package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class VerifyPinResponse implements SafeParcelable {
    public static final aj CREATOR;
    public final String rapt;
    public final int status;
    final int version;

    static {
        CREATOR = new aj();
    }

    VerifyPinResponse(int version, int status, String rapt) {
        this.version = version;
        this.status = status;
        this.rapt = rapt;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aj.a(this, dest, flags);
    }
}
