package com.google.android.gms.search.queries;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.QuerySpecification;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.search.queries.QueryCall.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, bVar.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, bVar.query, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, bVar.packageName, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, bVar.aMc, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, bVar.start);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, bVar.aLZ);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, bVar.aLU, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hR(x0);
    }

    public b hR(Parcel parcel) {
        int i = 0;
        QuerySpecification querySpecification = null;
        int bT = a.bT(parcel);
        int i2 = 0;
        String[] strArr = null;
        String str = null;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    querySpecification = (QuerySpecification) a.a(parcel, bS, QuerySpecification.CREATOR);
                    break;
                case 1000:
                    i3 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(i3, str2, str, strArr, i2, i, querySpecification);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] kC(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kC(x0);
    }
}
