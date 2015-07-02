package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.tt.c;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class tz implements Creator<c> {
    static void a(c cVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = cVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, cVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.a(parcel, 2, cVar.vf, true);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hd(x0);
    }

    public c hd(Parcel parcel) {
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(2));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new c(hashSet, i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public c[] jO(int i) {
        return new c[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jO(x0);
    }
}
