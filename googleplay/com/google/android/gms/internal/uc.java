package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.tt.g;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class uc implements Creator<g> {
    static void a(g gVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = gVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, gVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.a(parcel, 2, gVar.aKc);
        }
        if (set.contains(Integer.valueOf(3))) {
            b.a(parcel, 3, gVar.mValue, true);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hg(x0);
    }

    public g hg(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(3));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new g(hashSet, i, z, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public g[] jR(int i) {
        return new g[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jR(x0);
    }
}
