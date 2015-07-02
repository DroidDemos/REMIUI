package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ac implements Creator<ab> {
    static void a(ab abVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, abVar.versionCode);
        b.c(parcel, 2, abVar.statusCode);
        b.a(parcel, 3, abVar.aWT, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iP(x0);
    }

    public ab iP(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        al alVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    alVar = (al) a.a(parcel, bS, al.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ab(i2, i, alVar);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ab[] lP(int i) {
        return new ab[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lP(x0);
    }
}
