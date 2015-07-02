package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GplusInfoRequest implements SafeParcelable {
    public static final u CREATOR;
    CaptchaSolution Gu;
    String accountName;
    final int version;

    static {
        CREATOR = new u();
    }

    public GplusInfoRequest() {
        this.version = 1;
    }

    GplusInfoRequest(int version, String accountName, CaptchaSolution optionalCaptchaSolution) {
        this.version = version;
        this.accountName = accountName;
        this.Gu = optionalCaptchaSolution;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        u.a(this, dest, flags);
    }
}
