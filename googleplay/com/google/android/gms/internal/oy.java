package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class oy implements Creator<ox> {
    static void a(ox oxVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, oxVar.uid);
        b.c(parcel, 1000, oxVar.getVersionCode());
        b.a(parcel, 2, oxVar.packageName, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fH(x0);
    }

    public ox fH(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
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
            return new ox(i2, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ox[] hY(int i) {
        return new ox[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hY(x0);
    }
}
