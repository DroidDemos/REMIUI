package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CaptchaSolution implements SafeParcelable {
    public static final d CREATOR;
    String GP;
    String Iv;
    final int version;

    static {
        CREATOR = new d();
    }

    CaptchaSolution(int version, String token, String answer) {
        this.version = version;
        this.GP = token;
        this.Iv = answer;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
