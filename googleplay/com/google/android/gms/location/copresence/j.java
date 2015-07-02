package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.location.copresence.MessageFilter.a;
import com.google.android.wallet.instrumentmanager.R;

public class j implements Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, aVar.getVersionCode());
        b.a(parcel, 2, aVar.WP, false);
        b.a(parcel, 3, aVar.type, false);
        b.a(parcel, 4, aVar.auj, i, false);
        b.a(parcel, 5, aVar.auk);
        b.a(parcel, 6, aVar.aul, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fs(x0);
    }

    public a fs(Parcel parcel) {
        People people = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        long j = 0;
        AccessKey accessKey = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    accessKey = (AccessKey) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, AccessKey.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    people = (People) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, People.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new a(i, str2, str, accessKey, j, people);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public a[] hJ(int i) {
        return new a[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hJ(x0);
    }
}
