package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<CorpusStatus> {
    static void a(CorpusStatus corpusStatus, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, corpusStatus.CI);
        b.c(parcel, 1000, corpusStatus.mVersionCode);
        b.a(parcel, 2, corpusStatus.CJ);
        b.a(parcel, 3, corpusStatus.CK);
        b.a(parcel, 4, corpusStatus.CL);
        b.a(parcel, 5, corpusStatus.CM, false);
        b.a(parcel, 6, corpusStatus.CN, false);
        b.J(parcel, bU);
    }

    public CorpusStatus[] H(int i) {
        return new CorpusStatus[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return n(x0);
    }

    public CorpusStatus n(Parcel parcel) {
        String str = null;
        boolean z = false;
        long j = 0;
        int bT = a.bT(parcel);
        Bundle bundle = null;
        long j2 = 0;
        long j3 = 0;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
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
            return new CorpusStatus(i, z, j3, j2, j, bundle, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return H(x0);
    }
}
