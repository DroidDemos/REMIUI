package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.tt.b.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class tx implements Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = aVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, aVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.c(parcel, 2, aVar.aJV);
        }
        if (set.contains(Integer.valueOf(3))) {
            b.c(parcel, 3, aVar.aJW);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hb(x0);
    }

    public a hb(Parcel parcel) {
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        Set hashSet = new HashSet();
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(3));
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new a(hashSet, i3, i2, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public a[] jM(int i) {
        return new a[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jM(x0);
    }
}
