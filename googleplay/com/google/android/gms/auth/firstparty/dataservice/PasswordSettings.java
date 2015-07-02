package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PasswordSettings implements SafeParcelable {
    public static final ab CREATOR;
    public final String status;
    final int version;

    static {
        CREATOR = new ab();
    }

    PasswordSettings(int version, String status) {
        this.version = version;
        this.status = status;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ab.a(this, dest, flags);
    }
}
