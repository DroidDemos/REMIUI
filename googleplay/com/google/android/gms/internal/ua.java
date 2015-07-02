package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.tt.d;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class ua implements Creator<d> {
    static void a(d dVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = dVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, dVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.a(parcel, 2, dVar.aIX, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            b.a(parcel, 3, dVar.aJX, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            b.a(parcel, 4, dVar.aJa, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            b.a(parcel, 5, dVar.aJY, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            b.a(parcel, 6, dVar.aJZ, true);
        }
        if (set.contains(Integer.valueOf(7))) {
            b.a(parcel, 7, dVar.aKa, true);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return he(x0);
    }

    public d he(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str6 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str5 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str4 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str3 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(6));
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
            return new d(hashSet, i, str6, str5, str4, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public d[] jP(int i) {
        return new d[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jP(x0);
    }
}
