package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ae implements Creator<ad> {
    static void a(ad adVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, adVar.versionCode);
        b.a(parcel, 2, adVar.ml);
        b.a(parcel, 3, adVar.mv);
        b.J(parcel, bU);
    }

    public ad a(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ad(i, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ad[] b(int i) {
        return new ad[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return a(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return b(x0);
    }
}
