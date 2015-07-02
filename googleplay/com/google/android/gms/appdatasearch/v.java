package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class v implements Creator<PhraseAffinitySpecification> {
    static void a(PhraseAffinitySpecification phraseAffinitySpecification, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, phraseAffinitySpecification.DI, i, false);
        b.c(parcel, 1000, phraseAffinitySpecification.mVersionCode);
        b.J(parcel, bU);
    }

    public PhraseAffinitySpecification F(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        PhraseAffinityCorpusSpec[] phraseAffinityCorpusSpecArr = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
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
            return new PhraseAffinitySpecification(i, phraseAffinityCorpusSpecArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PhraseAffinitySpecification[] Z(int i) {
        return new PhraseAffinitySpecification[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return F(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return Z(x0);
    }
}
