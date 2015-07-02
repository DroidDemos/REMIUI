package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class VerifyPinRequest implements SafeParcelable {
    public static final ai CREATOR;
    public final String accountName;
    public final String pin;
    final int version;

    static {
        CREATOR = new ai();
    }

    VerifyPinRequest(int version, String accountName, String pin) {
        this.version = version;
        this.accountName = accountName;
        this.pin = pin;
    }

    public VerifyPinRequest(String accountName, String pin) {
        this(1, accountName, pin);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ai.a(this, dest, flags);
    }
}
