package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ProxyCard implements SafeParcelable {
    public static final Creator<ProxyCard> CREATOR;
    String aUv;
    String aUw;
    int aUx;
    int aUy;
    private final int mVersionCode;

    static {
        CREATOR = new o();
    }

    ProxyCard(int versionCode, String pan, String cvn, int expirationMonth, int expirationYear) {
        this.mVersionCode = versionCode;
        this.aUv = pan;
        this.aUw = cvn;
        this.aUx = expirationMonth;
        this.aUy = expirationYear;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel out, int flags) {
        o.a(this, out, flags);
    }
}
