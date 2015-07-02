package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PasswordCheckResponse implements SafeParcelable {
    public static final aa CREATOR;
    String Gx;
    String HD;
    String status;
    final int version;

    static {
        CREATOR = new aa();
    }

    PasswordCheckResponse(int version, String status, String passwordStrength, String detail) {
        this.version = version;
        this.status = status;
        this.HD = passwordStrength;
        this.Gx = detail;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aa.a(this, dest, flags);
    }
}
