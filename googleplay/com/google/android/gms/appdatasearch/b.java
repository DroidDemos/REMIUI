package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<CorpusScoringInfo> {
    static void a(CorpusScoringInfo corpusScoringInfo, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, corpusScoringInfo.corpus, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, corpusScoringInfo.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, corpusScoringInfo.weight);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public CorpusScoringInfo[] G(int i) {
        return new CorpusScoringInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m(x0);
    }

    public CorpusScoringInfo m(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        CorpusId corpusId = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int i3;
            CorpusId corpusId2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = i2;
                    CorpusId corpusId3 = (CorpusId) a.a(parcel, bS, CorpusId.CREATOR);
                    bS = i;
                    corpusId2 = corpusId3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bS = a.g(parcel, bS);
                    corpusId2 = corpusId;
                    i3 = i2;
                    break;
                case 1000:
                    int i4 = i;
                    corpusId2 = corpusId;
                    i3 = a.g(parcel, bS);
                    bS = i4;
                    break;
                default:
                    a.b(parcel, bS);
                    bS = i;
                    corpusId2 = corpusId;
                    i3 = i2;
                    break;
            }
            i2 = i3;
            corpusId = corpusId2;
            i = bS;
        }
        if (parcel.dataPosition() == bT) {
            return new CorpusScoringInfo(i2, corpusId, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return G(x0);
    }
}
