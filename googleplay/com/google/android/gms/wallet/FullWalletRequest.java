package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class FullWalletRequest implements SafeParcelable {
    public static final Creator<FullWalletRequest> CREATOR;
    String aTl;
    String aTm;
    Cart aTv;
    private final int mVersionCode;

    static {
        CREATOR = new f();
    }

    FullWalletRequest() {
        this.mVersionCode = 1;
    }

    FullWalletRequest(int versionCode, String googleTransactionId, String merchantTransactionId, Cart cart) {
        this.mVersionCode = versionCode;
        this.aTl = googleTransactionId;
        this.aTm = merchantTransactionId;
        this.aTv = cart;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
