package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PlusProfileCreationResponse implements SafeParcelable {
    public static final i CREATOR;
    String IK;
    final int version;

    static {
        CREATOR = new i();
    }

    PlusProfileCreationResponse(int version, String status) {
        this.version = version;
        this.IK = status;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
