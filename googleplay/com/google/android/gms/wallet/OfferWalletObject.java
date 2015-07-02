package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class OfferWalletObject implements SafeParcelable {
    public static final Creator<OfferWalletObject> CREATOR;
    String aUr;
    CommonWalletObject aUs;
    String fl;
    private final int mVersionCode;

    static {
        CREATOR = new n();
    }

    OfferWalletObject() {
        this.mVersionCode = 3;
    }

    OfferWalletObject(int versionCode, String id, String redemptionCode, CommonWalletObject commonWalletObject) {
        this.mVersionCode = versionCode;
        this.aUr = redemptionCode;
        if (versionCode < 3) {
            this.aUs = CommonWalletObject.vb().el(id).vc();
        } else {
            this.aUs = commonWalletObject;
        }
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
