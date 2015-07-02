package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class qd implements SafeParcelable {
    public static final Creator<qd> CREATOR;
    private final long EG;
    private final String Fl;
    private final String ayp;
    private final int mVersionCode;

    static {
        CREATOR = new qe();
    }

    qd(int i, String str, String str2, long j) {
        this.mVersionCode = i;
        this.Fl = str;
        this.ayp = str2;
        this.EG = j;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.Fl;
    }

    public String getClientInstanceId() {
        return this.ayp;
    }

    public long getTimestampMs() {
        return this.EG;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        qe.a(this, out, flags);
    }
}
