package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<CorpusId> {
    static void a(CorpusId corpusId, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, corpusId.packageName, false);
        b.c(parcel, 1000, corpusId.mVersionCode);
        b.a(parcel, 2, corpusId.corpusName, false);
        b.J(parcel, bU);
    }

    public CorpusId[] F(int i) {
        return new CorpusId[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return l(x0);
    }

    public CorpusId l(Parcel parcel) {
        String str = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CorpusId(i, str2, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return F(x0);
    }
}
