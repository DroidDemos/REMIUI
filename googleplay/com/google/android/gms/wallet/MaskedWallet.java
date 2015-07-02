package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class MaskedWallet implements SafeParcelable {
    public static final Creator<MaskedWallet> CREATOR;
    String aTl;
    String aTm;
    String aTo;
    Address aTp;
    Address aTq;
    String[] aTr;
    UserAddress aTs;
    UserAddress aTt;
    InstrumentInfo[] aTu;
    LoyaltyWalletObject[] aUf;
    OfferWalletObject[] aUg;
    private final int mVersionCode;

    static {
        CREATOR = new k();
    }

    private MaskedWallet() {
        this.mVersionCode = 2;
    }

    MaskedWallet(int versionCode, String googleTransactionId, String merchantTransactionId, String[] paymentDescriptions, String email, Address billingAddress, Address shippingAddress, LoyaltyWalletObject[] loyaltyWalletObjects, OfferWalletObject[] offerWalletObjects, UserAddress buyerBillingAddress, UserAddress buyerShippingAddress, InstrumentInfo[] instrumentInfos) {
        this.mVersionCode = versionCode;
        this.aTl = googleTransactionId;
        this.aTm = merchantTransactionId;
        this.aTr = paymentDescriptions;
        this.aTo = email;
        this.aTp = billingAddress;
        this.aTq = shippingAddress;
        this.aUf = loyaltyWalletObjects;
        this.aUg = offerWalletObjects;
        this.aTs = buyerBillingAddress;
        this.aTt = buyerShippingAddress;
        this.aTu = instrumentInfos;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
