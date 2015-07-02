package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CheckFactoryResetPolicyComplianceRequest implements SafeParcelable {
    public static final l CREATOR;
    public final String accountId;
    final int version;

    static {
        CREATOR = new l();
    }

    CheckFactoryResetPolicyComplianceRequest(int version, String accountId) {
        this.version = version;
        this.accountId = accountId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        l.a(this, dest, flags);
    }
}
