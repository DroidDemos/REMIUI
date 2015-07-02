package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class q implements Creator<p> {
    static void a(p pVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, pVar.versionCode);
        b.c(parcel, 2, pVar.statusCode);
        b.c(parcel, 3, pVar.aWN);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iJ(x0);
    }

    public p iJ(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
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
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new p(i3, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public p[] lJ(int i) {
        return new p[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lJ(x0);
    }
}
