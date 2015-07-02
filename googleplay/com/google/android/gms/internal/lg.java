package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class lg implements Creator<lf> {
    static void a(lf lfVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, lfVar.getVersionCode());
        b.a(parcel, 2, lfVar.iZ(), i, false);
        b.J(parcel, bU);
    }

    public lf bY(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        lh lhVar = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    lhVar = (lh) a.a(parcel, bS, lh.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new lf(i, lhVar);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bY(x0);
    }

    public lf[] dq(int i) {
        return new lf[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dq(x0);
    }
}
