package com.google.android.gms.auth.firstparty.shared;

import android.graphics.Bitmap;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CaptchaChallenge implements SafeParcelable {
    public static final c CREATOR;
    String GP;
    String Gv;
    Bitmap Iu;
    final int version;

    static {
        CREATOR = new c();
    }

    CaptchaChallenge(int version, String statusWireCode, String token, Bitmap captcha) {
        this.version = version;
        this.Gv = statusWireCode;
        this.GP = token;
        this.Iu = captcha;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
