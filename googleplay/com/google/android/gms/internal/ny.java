package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class ny implements Creator<nx> {
    static void a(nx nxVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, nxVar.getVersionCode());
        b.d(parcel, 2, nxVar.auF, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fy(x0);
    }

    public nx fy(Parcel parcel) {
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
                    arrayList = a.c(parcel, bS, oj.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new nx(i, arrayList);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public nx[] hP(int i) {
        return new nx[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hP(x0);
    }
}
