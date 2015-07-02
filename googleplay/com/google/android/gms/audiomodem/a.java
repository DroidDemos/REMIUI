package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<DecodedToken> {
    static void a(DecodedToken decodedToken, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, decodedToken.getVersionCode());
        b.a(parcel, 2, decodedToken.getToken(), false);
        b.c(parcel, 3, decodedToken.getRank());
        b.J(parcel, bU);
    }

    public DecodedToken T(Parcel parcel) {
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        byte[] bArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bArr = com.google.android.gms.common.internal.safeparcel.a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new DecodedToken(i2, bArr, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public DecodedToken[] as(int i) {
        return new DecodedToken[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return T(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return as(x0);
    }
}
