package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountRecoveryGuidanceRequest implements SafeParcelable {
    public static final f CREATOR;
    public final String accountName;
    final int version;

    static {
        CREATOR = new f();
    }

    AccountRecoveryGuidanceRequest(int version, String accountName) {
        this.version = version;
        this.accountName = accountName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
