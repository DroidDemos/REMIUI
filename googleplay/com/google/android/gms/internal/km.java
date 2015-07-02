package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.jw.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class km implements Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, aVar.getAccountName(), false);
        b.c(parcel, 1000, aVar.getVersionCode());
        b.c(parcel, 2, aVar.iH(), false);
        b.c(parcel, 3, aVar.iG());
        b.a(parcel, 4, aVar.iJ(), false);
        b.J(parcel, bU);
    }

    public a bR(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        List list = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new a(i2, str2, list, i, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bR(x0);
    }

    public a[] dj(int i) {
        return new a[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dj(x0);
    }
}
