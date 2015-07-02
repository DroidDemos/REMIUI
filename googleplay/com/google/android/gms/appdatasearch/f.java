package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<DocumentResults> {
    static void a(DocumentResults documentResults, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, documentResults.mErrorMessage, false);
        b.c(parcel, 1000, documentResults.mVersionCode);
        b.a(parcel, 2, documentResults.CV, false);
        b.a(parcel, 3, documentResults.CX, false);
        b.a(parcel, 4, documentResults.CY, false);
        b.J(parcel, bU);
    }

    public DocumentResults[] K(int i) {
        return new DocumentResults[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return q(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return K(x0);
    }

    public DocumentResults q(Parcel parcel) {
        Bundle bundle = null;
        int bT = a.bT(parcel);
        int i = 0;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle3 = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bundle2 = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle = a.r(parcel, bS);
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
            return new DocumentResults(i, str, bundle3, bundle2, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
