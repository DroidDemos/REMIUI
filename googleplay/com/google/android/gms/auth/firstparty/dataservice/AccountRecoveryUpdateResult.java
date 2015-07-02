package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountRecoveryUpdateResult implements SafeParcelable {
    public static final h CREATOR;
    public final String error;
    final int version;

    static {
        CREATOR = new h();
    }

    AccountRecoveryUpdateResult(int version, String error) {
        this.version = version;
        this.error = error;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
