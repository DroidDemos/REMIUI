package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<WalletFragmentInitParams> {
    static void a(WalletFragmentInitParams walletFragmentInitParams, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, walletFragmentInitParams.mVersionCode);
        b.a(parcel, 2, walletFragmentInitParams.getAccountName(), false);
        b.a(parcel, 3, walletFragmentInitParams.getMaskedWalletRequest(), i, false);
        b.c(parcel, 4, walletFragmentInitParams.getMaskedWalletRequestCode());
        b.a(parcel, 5, walletFragmentInitParams.getMaskedWallet(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return in(x0);
    }

    public WalletFragmentInitParams in(Parcel parcel) {
        MaskedWallet maskedWallet = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        int i2 = -1;
        MaskedWalletRequest maskedWalletRequest = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    maskedWalletRequest = (MaskedWalletRequest) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, MaskedWalletRequest.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    maskedWallet = (MaskedWallet) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, MaskedWallet.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new WalletFragmentInitParams(i, str, maskedWalletRequest, i2, maskedWallet);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public WalletFragmentInitParams[] lj(int i) {
        return new WalletFragmentInitParams[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lj(x0);
    }
}
