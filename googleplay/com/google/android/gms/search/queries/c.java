package com.google.android.gms.search.queries;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.PhraseAffinityCorpusSpec;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.search.queries.GetPhraseAffinityCall.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, bVar.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, bVar.aLW, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, bVar.aLX, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hN(x0);
    }

    public b hN(Parcel parcel) {
        PhraseAffinityCorpusSpec[] phraseAffinityCorpusSpecArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        String[] strArr = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    phraseAffinityCorpusSpecArr = (PhraseAffinityCorpusSpec[]) a.b(parcel, bS, PhraseAffinityCorpusSpec.CREATOR);
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
            return new b(i, strArr, phraseAffinityCorpusSpecArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] ky(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ky(x0);
    }
}
