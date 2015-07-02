package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PIMEUpdateResponse implements SafeParcelable {
    public static final s CREATOR;
    final String mErrorMessage;
    final int mVersionCode;
    public final byte[] nextIterToken;
    public final PIMEUpdate[] updates;

    static {
        CREATOR = new s();
    }

    PIMEUpdateResponse(int versionCode, String errorMessage, byte[] nextIterToken, PIMEUpdate[] updates) {
        this.mVersionCode = versionCode;
        this.mErrorMessage = errorMessage;
        this.nextIterToken = nextIterToken;
        this.updates = updates;
    }

    public int describeContents() {
        s sVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        s sVar = CREATOR;
        s.a(this, out, flags);
    }
}
