package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class oo implements Creator<on> {
    static void a(on onVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onVar.getVersionCode());
        b.a(parcel, 2, onVar.isActive());
        b.a(parcel, 3, onVar.pl());
        b.c(parcel, 4, onVar.pm());
        b.a(parcel, 5, onVar.pn());
        b.a(parcel, 6, onVar.po());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fD(x0);
    }

    public on fD(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        int i = 0;
        boolean z3 = false;
        boolean z4 = false;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z4 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new on(i2, z4, z3, i, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public on[] hU(int i) {
        return new on[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hU(x0);
    }
}
