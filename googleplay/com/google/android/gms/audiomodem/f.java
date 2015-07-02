package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.audiomodem.TokenBroadcaster.Params;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<Params> {
    static void a(Params params, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, params.getVersionCode());
        b.a(parcel, 2, params.getToken(), false);
        b.c(parcel, 3, params.getVolume());
        b.a(parcel, 4, params.getEncodings(), i, false);
        b.J(parcel, bU);
    }

    public Params Y(Parcel parcel) {
        Encoding[] encodingArr = null;
        int i = 0;
        int bT = a.bT(parcel);
        byte[] bArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bArr = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    encodingArr = (Encoding[]) a.b(parcel, bS, Encoding.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Params(i2, bArr, i, encodingArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Params[] aC(int i) {
        return new Params[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return Y(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aC(x0);
    }
}
