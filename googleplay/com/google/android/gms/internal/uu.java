package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.ut.a;
import com.google.android.wallet.instrumentmanager.R;

public class uu implements Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, aVar.mVersionCode);
        b.a(parcel, 1, aVar.packageName, false);
        b.a(parcel, 2, aVar.aLC);
        b.a(parcel, 3, aVar.blocked);
        b.a(parcel, 4, aVar.aLD);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hq(x0);
    }

    public a hq(Parcel parcel) {
        long j = 0;
        boolean z = false;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        String str = null;
        long j2 = 0;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, bS);
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
            return new a(i, str, j2, z, j);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public a[] kb(int i) {
        return new a[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kb(x0);
    }
}
