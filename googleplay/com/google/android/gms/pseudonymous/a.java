package com.google.android.gms.pseudonymous;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<PseudonymousIdToken> {
    static void a(PseudonymousIdToken pseudonymousIdToken, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, pseudonymousIdToken.mVersionCode);
        b.a(parcel, 2, pseudonymousIdToken.getValue(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hi(x0);
    }

    public PseudonymousIdToken hi(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PseudonymousIdToken(i, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public PseudonymousIdToken[] jT(int i) {
        return new PseudonymousIdToken[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jT(x0);
    }
}
