package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<e> {
    static void a(e eVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, eVar.getVersionCode());
        b.a(parcel, 2, eVar.qx(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gD(x0);
    }

    public e gD(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        a aVar = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    aVar = (a) a.a(parcel, bS, a.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new e(i, aVar);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public e[] jc(int i) {
        return new e[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jc(x0);
    }
}
