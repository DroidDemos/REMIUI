package com.google.android.gms.search.corpora;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.search.corpora.RequestIndexingCall.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, bVar.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, bVar.packageName, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, bVar.corpusName, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, bVar.aLM);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hz(x0);
    }

    public b hz(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        long j = 0;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(i, str2, str, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] kk(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kk(x0);
    }
}
