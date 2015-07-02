package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountRemovalRequest implements SafeParcelable {
    public static final i CREATOR;
    String accountName;
    final int version;

    static {
        CREATOR = new i();
    }

    public AccountRemovalRequest() {
        this.version = 1;
    }

    AccountRemovalRequest(int version, String accountName) {
        this.version = version;
        this.accountName = accountName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
