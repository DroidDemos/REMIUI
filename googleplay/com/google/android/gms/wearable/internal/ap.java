package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ap implements Creator<ao> {
    static void a(ao aoVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, aoVar.versionCode);
        b.c(parcel, 2, aoVar.statusCode);
        b.a(parcel, 3, aoVar.aWR, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iT(x0);
    }

    public ao iT(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        m mVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    mVar = (m) a.a(parcel, bS, m.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ao(i2, i, mVar);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ao[] lT(int i) {
        return new ao[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lT(x0);
    }
}
