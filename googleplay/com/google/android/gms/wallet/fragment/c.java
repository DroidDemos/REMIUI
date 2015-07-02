package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<WalletFragmentStyle> {
    static void a(WalletFragmentStyle walletFragmentStyle, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, walletFragmentStyle.mVersionCode);
        b.a(parcel, 2, walletFragmentStyle.aVe, false);
        b.c(parcel, 3, walletFragmentStyle.aVf);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ip(x0);
    }

    public WalletFragmentStyle ip(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new WalletFragmentStyle(i2, bundle, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public WalletFragmentStyle[] ll(int i) {
        return new WalletFragmentStyle[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ll(x0);
    }
}
