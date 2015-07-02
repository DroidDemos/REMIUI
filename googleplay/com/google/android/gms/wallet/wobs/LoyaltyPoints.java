package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LoyaltyPoints implements SafeParcelable {
    public static final Creator<LoyaltyPoints> CREATOR;
    TimeInterval aTU;
    LoyaltyPointsBalance aVP;
    String label;
    private final int mVersionCode;
    String type;

    static {
        CREATOR = new e();
    }

    LoyaltyPoints() {
        this.mVersionCode = 1;
    }

    LoyaltyPoints(int versionCode, String label, LoyaltyPointsBalance balance, String type, TimeInterval validTimeInterval) {
        this.mVersionCode = versionCode;
        this.label = label;
        this.aVP = balance;
        this.type = type;
        this.aTU = validTimeInterval;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
