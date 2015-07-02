package com.google.android.gms.search.queries;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.GlobalSearchQuerySpecification;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.search.queries.GlobalQueryCall.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, bVar.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, bVar.query, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, bVar.start);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, bVar.aLZ);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, bVar.aMa, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hP(x0);
    }

    public b hP(Parcel parcel) {
        GlobalSearchQuerySpecification globalSearchQuerySpecification = null;
        int i = 0;
        int bT = a.bT(parcel);
        int i2 = 0;
        String str = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    globalSearchQuerySpecification = (GlobalSearchQuerySpecification) a.a(parcel, bS, GlobalSearchQuerySpecification.CREATOR);
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
            return new b(i3, str, i2, i, globalSearchQuerySpecification);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] kA(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kA(x0);
    }
}
