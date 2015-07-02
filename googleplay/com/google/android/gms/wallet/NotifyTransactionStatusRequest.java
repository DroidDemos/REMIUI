package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class NotifyTransactionStatusRequest implements SafeParcelable {
    public static final Creator<NotifyTransactionStatusRequest> CREATOR;
    String aTl;
    String aUp;
    final int mVersionCode;
    int status;

    static {
        CREATOR = new m();
    }

    NotifyTransactionStatusRequest() {
        this.mVersionCode = 1;
    }

    NotifyTransactionStatusRequest(int versionCode, String googleTransactionId, int status, String detailedReason) {
        this.mVersionCode = versionCode;
        this.aTl = googleTransactionId;
        this.status = status;
        this.aUp = detailedReason;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        m.a(this, out, flags);
    }
}
