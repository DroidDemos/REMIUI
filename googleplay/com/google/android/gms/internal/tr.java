package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class tr implements Creator<tq> {
    static void a(tq tqVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = tqVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, tqVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.a(parcel, 2, tqVar.CB, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            b.a(parcel, 4, tqVar.aJu, i, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            b.a(parcel, 5, tqVar.aJm, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            b.a(parcel, 6, tqVar.aJv, i, true);
        }
        if (set.contains(Integer.valueOf(7))) {
            b.a(parcel, 7, tqVar.vc, true);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gX(x0);
    }

    public tq gX(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        to toVar = null;
        String str2 = null;
        to toVar2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            to toVar3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    toVar3 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(4));
                    toVar2 = toVar3;
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    toVar3 = (to) a.a(parcel, bS, to.CREATOR);
                    hashSet.add(Integer.valueOf(6));
                    toVar = toVar3;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(7));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new tq(hashSet, i, str3, toVar2, str2, toVar, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public tq[] jI(int i) {
        return new tq[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jI(x0);
    }
}
