package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LoyaltyPointsBalance implements SafeParcelable {
    public static final Creator<LoyaltyPointsBalance> CREATOR;
    int aVR;
    String aVS;
    double aVT;
    String aVU;
    long aVV;
    int aVW;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    LoyaltyPointsBalance() {
        this.mVersionCode = 1;
        this.aVW = -1;
        this.aVR = -1;
        this.aVT = -1.0d;
    }

    LoyaltyPointsBalance(int versionCode, int balanceInt, String balanceString, double balanceDouble, String currencyCode, long currencyMicros, int balanceType) {
        this.mVersionCode = versionCode;
        this.aVR = balanceInt;
        this.aVS = balanceString;
        this.aVT = balanceDouble;
        this.aVU = currencyCode;
        this.aVV = currencyMicros;
        this.aVW = balanceType;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
