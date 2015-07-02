package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragmentInitParams implements SafeParcelable {
    public static final Creator<WalletFragmentInitParams> CREATOR;
    private String Fl;
    private MaskedWalletRequest aUM;
    private MaskedWallet aUN;
    private int aVa;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    private WalletFragmentInitParams() {
        this.mVersionCode = 1;
        this.aVa = -1;
    }

    WalletFragmentInitParams(int versionCode, String accountName, MaskedWalletRequest maskedWalletRequest, int maskedWalletRequestCode, MaskedWallet maskedWallet) {
        this.mVersionCode = versionCode;
        this.Fl = accountName;
        this.aUM = maskedWalletRequest;
        this.aVa = maskedWalletRequestCode;
        this.aUN = maskedWallet;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.Fl;
    }

    public MaskedWallet getMaskedWallet() {
        return this.aUN;
    }

    public MaskedWalletRequest getMaskedWalletRequest() {
        return this.aUM;
    }

    public int getMaskedWalletRequestCode() {
        return this.aVa;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
