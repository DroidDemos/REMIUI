package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletObjectMessage implements SafeParcelable {
    public static final Creator<WalletObjectMessage> CREATOR;
    String aVY;
    TimeInterval aWb;
    UriData aWc;
    UriData aWd;
    private final int mVersionCode;
    String tU;

    static {
        CREATOR = new i();
    }

    WalletObjectMessage() {
        this.mVersionCode = 1;
    }

    WalletObjectMessage(int versionCode, String header, String body, TimeInterval displayInterval, UriData actionUri, UriData imageUri) {
        this.mVersionCode = versionCode;
        this.aVY = header;
        this.tU = body;
        this.aWb = displayInterval;
        this.aWc = actionUri;
        this.aWd = imageUri;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
