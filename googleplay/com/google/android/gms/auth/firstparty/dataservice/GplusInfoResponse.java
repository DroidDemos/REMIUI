package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GplusInfoResponse implements SafeParcelable {
    public static final v CREATOR;
    String HA;
    String Hu;
    boolean Hv;
    String Hw;
    boolean Hx;
    boolean Hy;
    String Hz;
    String firstName;
    String lastName;
    final int version;

    static {
        CREATOR = new v();
    }

    GplusInfoResponse(int version, boolean isAllowed, String firstName, String lastName, String picasaUserName, boolean isGooglePlusEnabled, boolean isEsMobileEnabled, String ropText, String ropRevision, String wireCode) {
        this.version = version;
        this.Hv = isAllowed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Hw = picasaUserName;
        this.Hx = isGooglePlusEnabled;
        this.Hy = isEsMobileEnabled;
        this.Hz = ropText;
        this.Hu = ropRevision;
        this.HA = wireCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        v.a(this, dest, flags);
    }
}
