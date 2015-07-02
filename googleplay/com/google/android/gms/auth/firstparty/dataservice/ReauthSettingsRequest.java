package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ReauthSettingsRequest implements SafeParcelable {
    public static final ad CREATOR;
    public final String accountName;
    public final boolean force;
    final int version;

    static {
        CREATOR = new ad();
    }

    ReauthSettingsRequest(int version, String accountName, boolean force) {
        this.version = version;
        this.accountName = accountName;
        this.force = force;
    }

    public ReauthSettingsRequest(String accountName, boolean force) {
        this(1, accountName, force);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ad.a(this, dest, flags);
    }
}
