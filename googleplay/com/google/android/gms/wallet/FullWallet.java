package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class FullWallet implements SafeParcelable {
    public static final Creator<FullWallet> CREATOR;
    String aTl;
    String aTm;
    ProxyCard aTn;
    String aTo;
    Address aTp;
    Address aTq;
    String[] aTr;
    UserAddress aTs;
    UserAddress aTt;
    InstrumentInfo[] aTu;
    private final int mVersionCode;

    static {
        CREATOR = new e();
    }

    private FullWallet() {
        this.mVersionCode = 1;
    }

    FullWallet(int versionCode, String googleTransactionId, String merchantTransactionId, ProxyCard proxyCard, String email, Address billingAddress, Address shippingAddress, String[] paymentDescriptions, UserAddress buyerBillingAddress, UserAddress buyerShippingAddress, InstrumentInfo[] instrumentInfos) {
        this.mVersionCode = versionCode;
        this.aTl = googleTransactionId;
        this.aTm = merchantTransactionId;
        this.aTn = proxyCard;
        this.aTo = email;
        this.aTp = billingAddress;
        this.aTq = shippingAddress;
        this.aTr = paymentDescriptions;
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

    public void writeToParcel(Parcel out, int flags) {
        e.a(this, out, flags);
    }
}
