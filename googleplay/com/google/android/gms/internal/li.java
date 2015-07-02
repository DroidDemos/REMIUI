package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class li implements Creator<lh> {
    static void a(lh lhVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, lhVar.getVersionCode());
        b.d(parcel, 2, lhVar.jb(), false);
        b.J(parcel, bU);
    }

    public lh bZ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    arrayList = a.c(parcel, bS, lh.a.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new lh(i, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bZ(x0);
    }

    public lh[] dr(int i) {
        return new lh[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dr(x0);
    }
}
