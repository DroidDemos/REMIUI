package com.google.android.gms.maps.model.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<c> {
    static void a(c cVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, cVar.getVersionCode());
        b.c(parcel, 2, cVar.getType());
        b.a(parcel, 3, cVar.qw(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gC(x0);
    }

    public c gC(Parcel parcel) {
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
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bundle = a.r(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new c(i2, i, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public c[] jb(int i) {
        return new c[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jb(x0);
    }
}
