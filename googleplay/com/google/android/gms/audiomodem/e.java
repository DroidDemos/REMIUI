package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.audiomodem.Snoop.Params;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<Params> {
    static void a(Params params, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, params.getVersionCode());
        b.a(parcel, 2, params.getEncodings(), i, false);
        b.a(parcel, 3, params.shouldCallbackOnBroadcasterDetected());
        b.a(parcel, 4, params.shouldCallbackOnNoBroadcasterDetected());
        b.a(parcel, 5, params.getDurationWithNoBroadcasterMillis());
        b.J(parcel, bU);
    }

    public Params X(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        Encoding[] encodingArr = null;
        long j = 0;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    encodingArr = (Encoding[]) a.b(parcel, bS, Encoding.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    j = a.i(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Params(i, encodingArr, z2, z, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Params[] aB(int i) {
        return new Params[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return X(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aB(x0);
    }
}
