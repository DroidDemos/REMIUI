package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class WebSetupConfig implements SafeParcelable {
    public static final ak CREATOR;
    public final String url;
    final int version;

    static {
        CREATOR = new ak();
    }

    WebSetupConfig(int version, String url) {
        this.version = version;
        this.url = url;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ak.a(this, dest, flags);
    }
}
