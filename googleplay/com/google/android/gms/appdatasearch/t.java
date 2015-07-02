package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class t implements Creator<PhraseAffinityCorpusSpec> {
    static void a(PhraseAffinityCorpusSpec phraseAffinityCorpusSpec, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, phraseAffinityCorpusSpec.corpus, i, false);
        b.c(parcel, 1000, phraseAffinityCorpusSpec.mVersionCode);
        b.a(parcel, 2, phraseAffinityCorpusSpec.DE, false);
        b.J(parcel, bU);
    }

    public PhraseAffinityCorpusSpec D(Parcel parcel) {
        Bundle bundle = null;
        int bT = a.bT(parcel);
        int i = 0;
        CorpusId corpusId = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            Bundle bundle2;
            CorpusId corpusId2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    CorpusId corpusId3 = (CorpusId) a.a(parcel, bS, CorpusId.CREATOR);
                    bundle2 = bundle;
                    corpusId2 = corpusId3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle2 = a.r(parcel, bS);
                    corpusId2 = corpusId;
                    i2 = i;
                    break;
                case 1000:
                    Bundle bundle3 = bundle;
                    corpusId2 = corpusId;
                    i2 = a.g(parcel, bS);
                    bundle2 = bundle3;
                    break;
                default:
                    a.b(parcel, bS);
                    bundle2 = bundle;
                    corpusId2 = corpusId;
                    i2 = i;
                    break;
            }
            i = i2;
            corpusId = corpusId2;
            bundle = bundle2;
        }
        if (parcel.dataPosition() == bT) {
            return new PhraseAffinityCorpusSpec(i, corpusId, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PhraseAffinityCorpusSpec[] X(int i) {
        return new PhraseAffinityCorpusSpec[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return D(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return X(x0);
    }
}
