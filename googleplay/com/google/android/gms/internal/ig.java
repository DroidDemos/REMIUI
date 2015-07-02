package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ig implements Creator<if> {
    static void a(if ifVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, ifVar.responseCode);
        b.c(parcel, 1000, ifVar.versionCode);
        b.a(parcel, 2, ifVar.Gp, false);
        b.a(parcel, 3, ifVar.Gq, false);
        b.J(parcel, bU);
    }

    public if[] aJ(int i) {
        return new if[i];
    }

    public if af(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bArr = a.s(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new if(i2, i, bundle, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return af(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aJ(x0);
    }
}
