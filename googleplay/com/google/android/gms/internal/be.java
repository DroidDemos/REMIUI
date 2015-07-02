package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class be implements Creator<bd> {
    static void a(bd bdVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, bdVar.versionCode);
        b.a(parcel, 2, bdVar.or, false);
        b.c(parcel, 3, bdVar.height);
        b.c(parcel, 4, bdVar.heightPixels);
        b.a(parcel, 5, bdVar.os);
        b.c(parcel, 6, bdVar.width);
        b.c(parcel, 7, bdVar.widthPixels);
        b.a(parcel, 8, bdVar.ot, i, false);
        b.J(parcel, bU);
    }

    public bd c(Parcel parcel) {
        bd[] bdVarArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        int i5 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    bdVarArr = (bd[]) a.b(parcel, bS, bd.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new bd(i5, str, i4, i3, z, i2, i, bdVarArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return c(x0);
    }

    public bd[] g(int i) {
        return new bd[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return g(x0);
    }
}
