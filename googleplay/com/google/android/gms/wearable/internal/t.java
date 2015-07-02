package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.ConnectionConfiguration;

public class t implements SafeParcelable {
    public static final Creator<t> CREATOR;
    public final ConnectionConfiguration[] aWP;
    public final int statusCode;
    public final int versionCode;

    static {
        CREATOR = new u();
    }

    t(int i, int i2, ConnectionConfiguration[] connectionConfigurationArr) {
        this.versionCode = i;
        this.statusCode = i2;
        this.aWP = connectionConfigurationArr;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        u.a(this, dest, flags);
    }
}
