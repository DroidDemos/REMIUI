package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.tt.h;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class ud implements Creator<h> {
    static void a(h hVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = hVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, hVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(3))) {
            b.c(parcel, 3, hVar.rW());
        }
        if (set.contains(Integer.valueOf(4))) {
            b.a(parcel, 4, hVar.mValue, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            b.a(parcel, 5, hVar.ajQ, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            b.c(parcel, 6, hVar.EB);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hh(x0);
    }

    public h hh(Parcel parcel) {
        String str = null;
        int i = 0;
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i2 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(6));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new h(hashSet, i3, str2, i2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public h[] jS(int i) {
        return new h[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jS(x0);
    }
}
