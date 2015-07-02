package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.ConnectionConfiguration;

@Deprecated
public class r implements SafeParcelable {
    public static final Creator<r> CREATOR;
    public final ConnectionConfiguration aWO;
    public final int statusCode;
    public final int versionCode;

    static {
        CREATOR = new s();
    }

    r(int i, int i2, ConnectionConfiguration connectionConfiguration) {
        this.versionCode = i;
        this.statusCode = i2;
        this.aWO = connectionConfiguration;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        s.a(this, dest, flags);
    }
}
