package com.google.android.gms.search.queries;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.QuerySpecification;
import com.google.android.gms.search.queries.GetDocumentsCall.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, bVar.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, bVar.packageName, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, bVar.corpusName, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, bVar.aLT, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, bVar.aLU, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hL(x0);
    }

    public b hL(Parcel parcel) {
        QuerySpecification querySpecification = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        String[] strArr = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    strArr = com.google.android.gms.common.internal.safeparcel.a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    querySpecification = (QuerySpecification) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, QuerySpecification.CREATOR);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(i, str2, str, strArr, querySpecification);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] kw(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kw(x0);
    }
}
