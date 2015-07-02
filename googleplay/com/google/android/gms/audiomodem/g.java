package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.audiomodem.TokenReceiver.Params;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<Params> {
    static void a(Params params, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, params.getVersionCode());
        b.a(parcel, 2, params.getEncodings(), i, false);
        b.J(parcel, bU);
    }

    public Params Z(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Encoding[] encodingArr = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    encodingArr = (Encoding[]) a.b(parcel, bS, Encoding.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Params(i, encodingArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Params[] aD(int i) {
        return new Params[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return Z(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aD(x0);
    }
}
