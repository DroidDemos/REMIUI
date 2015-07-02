package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class qe implements Creator<qd> {
    static void a(qd qdVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, qdVar.getVersionCode());
        b.a(parcel, 2, qdVar.getAccountName(), false);
        b.a(parcel, 3, qdVar.getClientInstanceId(), false);
        b.a(parcel, 4, qdVar.getTimestampMs());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gi(x0);
    }

    public qd gi(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = a.i(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new qd(i, str2, str, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public qd[] iH(int i) {
        return new qd[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iH(x0);
    }
}
