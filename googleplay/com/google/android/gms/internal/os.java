package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class os implements Creator<or> {
    static void a(or orVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, orVar.getVersionCode());
        b.c(parcel, 2, orVar.type);
        b.a(parcel, 3, orVar.avg, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fF(x0);
    }

    public or fF(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
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
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new or(i2, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public or[] hW(int i) {
        return new or[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hW(x0);
    }
}
