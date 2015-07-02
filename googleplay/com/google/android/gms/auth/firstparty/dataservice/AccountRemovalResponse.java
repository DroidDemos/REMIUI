package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountRemovalResponse implements SafeParcelable {
    public static final j CREATOR;
    final String Gv;
    final int version;

    static {
        CREATOR = new j();
    }

    AccountRemovalResponse(int version, String statusWireCode) {
        this.version = version;
        this.Gv = statusWireCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
