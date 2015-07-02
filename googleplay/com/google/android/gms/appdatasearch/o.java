package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class o implements Creator<GlobalSearchCorpusConfig> {
    static void a(GlobalSearchCorpusConfig globalSearchCorpusConfig, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, globalSearchCorpusConfig.globalSearchSectionMappings, false);
        b.c(parcel, 1000, globalSearchCorpusConfig.mVersionCode);
        b.a(parcel, 2, globalSearchCorpusConfig.features, i, false);
        b.J(parcel, bU);
    }

    public GlobalSearchCorpusConfig[] S(int i) {
        return new GlobalSearchCorpusConfig[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return y(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return S(x0);
    }

    public GlobalSearchCorpusConfig y(Parcel parcel) {
        Feature[] featureArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        int[] iArr = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    iArr = a.v(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    featureArr = (Feature[]) a.b(parcel, bS, Feature.CREATOR);
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
            return new GlobalSearchCorpusConfig(i, iArr, featureArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
