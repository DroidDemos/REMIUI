package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.internal.tt.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class tw implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        Set set = bVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, bVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, bVar.aJS, i, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, bVar.aJT, i, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, bVar.aJU);
        }
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ha(x0);
    }

    public b ha(Parcel parcel) {
        b.b bVar = null;
        int i = 0;
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        b.a aVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    b.a aVar2 = (b.a) a.a(parcel, bS, b.a.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    aVar = aVar2;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    b.b bVar2 = (b.b) a.a(parcel, bS, b.b.CREATOR);
                    hashSet.add(Integer.valueOf(3));
                    bVar = bVar2;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(hashSet, i2, aVar, bVar, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] jL(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jL(x0);
    }
}
