package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class le implements Creator<ld> {
    static void a(ld ldVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, ldVar.mVersionCode);
        b.a(parcel, 2, ldVar.WP, false);
        b.c(parcel, 3, ldVar.WQ);
        b.J(parcel, bU);
    }

    public ld bX(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ld(i2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bX(x0);
    }

    public ld[] dp(int i) {
        return new ld[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dp(x0);
    }
}
