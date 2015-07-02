package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class AccountNameCheckResponse implements SafeParcelable {
    public static final b CREATOR;
    String Gv;
    List<String> Gw;
    String Gx;
    CaptchaChallenge Gy;
    final int version;

    static {
        CREATOR = new b();
    }

    AccountNameCheckResponse(int version, String statusWireCode, List<String> suggestions, String detail, CaptchaChallenge captcha) {
        this.version = version;
        this.Gv = statusWireCode;
        this.Gw = suggestions;
        this.Gx = detail;
        this.Gy = captcha;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
