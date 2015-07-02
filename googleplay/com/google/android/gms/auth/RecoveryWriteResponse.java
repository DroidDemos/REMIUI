package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RecoveryWriteResponse implements SafeParcelable {
    public static final e CREATOR;
    public String mErrorCode;
    final int mVersionCode;

    static {
        CREATOR = new e();
    }

    public RecoveryWriteResponse() {
        this.mVersionCode = 1;
    }

    RecoveryWriteResponse(int versionCode, String errorCode) {
        this.mVersionCode = versionCode;
        this.mErrorCode = errorCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        e.a(this, out, flags);
    }
}
