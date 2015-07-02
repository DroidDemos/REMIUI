package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OtpResponse implements SafeParcelable {
    public static final y CREATOR;
    final int Gf;
    public final String otp;

    static {
        CREATOR = new y();
    }

    OtpResponse(int version, String otp) {
        this.Gf = version;
        this.otp = otp;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        y.a(this, dest, flags);
    }
}
