package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class p implements Creator<GlobalSearchQuerySpecification> {
    static void a(GlobalSearchQuerySpecification globalSearchQuerySpecification, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, globalSearchQuerySpecification.Dh, i, false);
        b.c(parcel, 1000, globalSearchQuerySpecification.mVersionCode);
        b.c(parcel, 2, globalSearchQuerySpecification.scoringVerbosityLevel);
        b.a(parcel, 3, globalSearchQuerySpecification.Di, i, false);
        b.c(parcel, 4, globalSearchQuerySpecification.debugLevel);
        b.c(parcel, 5, globalSearchQuerySpecification.rankingStrategy);
        b.c(parcel, 6, globalSearchQuerySpecification.queryTokenizer);
        b.a(parcel, 7, globalSearchQuerySpecification.context, false);
        b.J(parcel, bU);
    }

    public GlobalSearchQuerySpecification[] T(int i) {
        return new GlobalSearchQuerySpecification[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return z(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return T(x0);
    }

    public GlobalSearchQuerySpecification z(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        int i3 = 0;
        CorpusScoringInfo[] corpusScoringInfoArr = null;
        int i4 = 0;
        CorpusId[] corpusIdArr = null;
        int i5 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    corpusIdArr = (CorpusId[]) a.b(parcel, bS, CorpusId.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    corpusScoringInfoArr = (CorpusScoringInfo[]) a.b(parcel, bS, CorpusScoringInfo.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.p(parcel, bS);
                    break;
                case 1000:
                    i5 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GlobalSearchQuerySpecification(i5, corpusIdArr, i4, corpusScoringInfoArr, i3, i2, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
