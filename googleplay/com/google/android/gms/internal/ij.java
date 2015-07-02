package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ij implements Creator<ii> {
    static void a(ii iiVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, iiVar.getVersionCode());
        b.a(parcel, 2, iiVar.hp(), false);
        b.J(parcel, bU);
    }

    public ii bJ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ii(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ii[] cE(int i) {
        return new ii[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bJ(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cE(x0);
    }
}
