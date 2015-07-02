package com.google.android.gms.plus.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class PlusCommonExtras implements SafeParcelable {
    public static final g CREATOR;
    public static String TAG;
    private String aHU;
    private String aHV;
    private final int mVersionCode;

    static {
        TAG = "PlusCommonExtras";
        CREATOR = new g();
    }

    public PlusCommonExtras() {
        this.mVersionCode = 1;
        this.aHU = "";
        this.aHV = "";
    }

    PlusCommonExtras(int versionCode, String gpsrc, String clientCallingPackage) {
        this.mVersionCode = versionCode;
        this.aHU = gpsrc;
        this.aHV = clientCallingPackage;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusCommonExtras)) {
            return false;
        }
        PlusCommonExtras plusCommonExtras = (PlusCommonExtras) obj;
        return this.mVersionCode == plusCommonExtras.mVersionCode && kl.equal(this.aHU, plusCommonExtras.aHU) && kl.equal(this.aHV, plusCommonExtras.aHV);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), this.aHU, this.aHV);
    }

    public String rx() {
        return this.aHU;
    }

    public String ry() {
        return this.aHV;
    }

    public String toString() {
        return kl.j(this).a("versionCode", Integer.valueOf(this.mVersionCode)).a("Gpsrc", this.aHU).a("ClientCallingPackage", this.aHV).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        g.a(this, out, flags);
    }
}
