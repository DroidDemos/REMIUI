package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CheckFactoryResetPolicyComplianceResponse implements SafeParcelable {
    public static final m CREATOR;
    public final boolean isCompliant;
    final int version;

    static {
        CREATOR = new m();
    }

    CheckFactoryResetPolicyComplianceResponse(int version, boolean isCompliant) {
        this.version = version;
        this.isCompliant = isCompliant;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        m.a(this, dest, flags);
    }
}
