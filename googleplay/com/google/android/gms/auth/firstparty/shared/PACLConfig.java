package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PACLConfig implements SafeParcelable {
    public static final h CREATOR;
    String II;
    String IJ;
    final int version;

    static {
        CREATOR = new h();
    }

    PACLConfig(int version, String visibleActions, String pacl) {
        this.version = version;
        this.II = visibleActions;
        this.IJ = pacl;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
