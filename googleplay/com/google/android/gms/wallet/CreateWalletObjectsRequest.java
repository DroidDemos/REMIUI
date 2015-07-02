package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CreateWalletObjectsRequest implements SafeParcelable {
    public static final Creator<CreateWalletObjectsRequest> CREATOR;
    LoyaltyWalletObject aTj;
    OfferWalletObject aTk;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    CreateWalletObjectsRequest() {
        this.mVersionCode = 2;
    }

    CreateWalletObjectsRequest(int versionCode, LoyaltyWalletObject loyaltyWalletObject, OfferWalletObject offerWalletObject) {
        this.mVersionCode = versionCode;
        this.aTj = loyaltyWalletObject;
        this.aTk = offerWalletObject;
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
