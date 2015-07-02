package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ta implements SafeParcelable {
    public static final tc CREATOR;
    public final long aHb;
    public final byte[] aHc;
    public final Bundle aHd;
    public final String tag;
    public final int versionCode;

    static {
        CREATOR = new tc();
    }

    ta(int i, long j, String str, byte[] bArr, Bundle bundle) {
        this.versionCode = i;
        this.aHb = j;
        this.tag = str;
        this.aHc = bArr;
        this.aHd = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tag=").append(this.tag).append(",");
        stringBuilder.append("eventTime=").append(this.aHb).append(",");
        if (!(this.aHd == null || this.aHd.isEmpty())) {
            stringBuilder.append("keyValues=");
            for (String str : this.aHd.keySet()) {
                stringBuilder.append("(").append(str).append(",");
                stringBuilder.append(this.aHd.getString(str)).append(")");
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        tc.a(this, out, flags);
    }
}
