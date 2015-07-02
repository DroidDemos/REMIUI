package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DecodedToken implements SafeParcelable {
    public static final Creator<DecodedToken> CREATOR;
    private final byte[] Fv;
    private final int Fw;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    DecodedToken(int versionCode, byte[] token, int rank) {
        this.mVersionCode = versionCode;
        this.Fv = token;
        this.Fw = rank;
    }

    public int describeContents() {
        return 0;
    }

    public int getRank() {
        return this.Fw;
    }

    public byte[] getToken() {
        return this.Fv;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
