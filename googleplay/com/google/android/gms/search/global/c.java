package com.google.android.gms.search.global;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.Feature;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.global.GetGlobalSearchSourcesCall.CorpusInfo;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<CorpusInfo> {
    static void a(CorpusInfo corpusInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, corpusInfo.mVersionCode);
        b.a(parcel, 1, corpusInfo.corpusName, false);
        b.a(parcel, 2, corpusInfo.features, i, false);
        b.a(parcel, 3, corpusInfo.isAppHistoryCorpus);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hD(x0);
    }

    public CorpusInfo hD(Parcel parcel) {
        Feature[] featureArr = null;
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int i2;
            String p;
            boolean z2;
            Feature[] featureArr2;
            int bS = a.bS(parcel);
            boolean z3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Feature[] featureArr3 = featureArr;
                    p = a.p(parcel, bS);
                    z2 = z;
                    featureArr2 = featureArr3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    p = str;
                    i2 = i;
                    z3 = z;
                    featureArr2 = (Feature[]) a.b(parcel, bS, Feature.CREATOR);
                    z2 = z3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    featureArr2 = featureArr;
                    p = str;
                    i2 = i;
                    break;
                case 1000:
                    z3 = z;
                    featureArr2 = featureArr;
                    p = str;
                    i2 = a.g(parcel, bS);
                    z2 = z3;
                    break;
                default:
                    a.b(parcel, bS);
                    z2 = z;
                    featureArr2 = featureArr;
                    p = str;
                    i2 = i;
                    break;
            }
            i = i2;
            str = p;
            featureArr = featureArr2;
            z = z2;
        }
        if (parcel.dataPosition() == bT) {
            return new CorpusInfo(i, str, featureArr, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CorpusInfo[] ko(int i) {
        return new CorpusInfo[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ko(x0);
    }
}
