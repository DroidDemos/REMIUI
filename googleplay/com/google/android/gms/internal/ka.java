package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ka implements Creator<jz> {
    static void a(jz jzVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, jzVar.version);
        b.c(parcel, 2, jzVar.VH);
        b.c(parcel, 3, jzVar.VI);
        b.a(parcel, 4, jzVar.VJ, false);
        b.a(parcel, 5, jzVar.VK, false);
        b.a(parcel, 6, jzVar.VL, i, false);
        b.a(parcel, 7, jzVar.VM, false);
        b.J(parcel, bU);
    }

    public jz bQ(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int bT = a.bT(parcel);
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    scopeArr = (Scope[]) a.b(parcel, bS, Scope.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    bundle = a.r(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new jz(i3, i2, i, str, iBinder, scopeArr, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bQ(x0);
    }

    public jz[] df(int i) {
        return new jz[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return df(x0);
    }
}
