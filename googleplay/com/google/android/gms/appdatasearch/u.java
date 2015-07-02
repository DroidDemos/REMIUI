package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class u implements Creator<PhraseAffinityResponse> {
    static void a(PhraseAffinityResponse phraseAffinityResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, phraseAffinityResponse.mErrorMessage, false);
        b.c(parcel, 1000, phraseAffinityResponse.mVersionCode);
        b.a(parcel, 2, phraseAffinityResponse.DG, i, false);
        b.a(parcel, 3, phraseAffinityResponse.DH, false);
        b.J(parcel, bU);
    }

    public PhraseAffinityResponse E(Parcel parcel) {
        int[] iArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        CorpusId[] corpusIdArr = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            String p;
            int[] iArr2;
            CorpusId[] corpusIdArr2;
            int bS = a.bS(parcel);
            int[] iArr3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    CorpusId[] corpusIdArr3 = corpusIdArr;
                    p = a.p(parcel, bS);
                    iArr2 = iArr;
                    corpusIdArr2 = corpusIdArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    p = str;
                    i2 = i;
                    iArr3 = iArr;
                    corpusIdArr2 = (CorpusId[]) a.b(parcel, bS, CorpusId.CREATOR);
                    iArr2 = iArr3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    iArr2 = a.v(parcel, bS);
                    corpusIdArr2 = corpusIdArr;
                    p = str;
                    i2 = i;
                    break;
                case 1000:
                    iArr3 = iArr;
                    corpusIdArr2 = corpusIdArr;
                    p = str;
                    i2 = a.g(parcel, bS);
                    iArr2 = iArr3;
                    break;
                default:
                    a.b(parcel, bS);
                    iArr2 = iArr;
                    corpusIdArr2 = corpusIdArr;
                    p = str;
                    i2 = i;
                    break;
            }
            i = i2;
            str = p;
            corpusIdArr = corpusIdArr2;
            iArr = iArr2;
        }
        if (parcel.dataPosition() == bT) {
            return new PhraseAffinityResponse(i, str, corpusIdArr, iArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PhraseAffinityResponse[] Y(int i) {
        return new PhraseAffinityResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return E(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return Y(x0);
    }
}
