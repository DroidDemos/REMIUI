package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.tt.f;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashSet;
import java.util.Set;

public class ub implements Creator<f> {
    static void a(f fVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        Set set = fVar.aIz;
        if (set.contains(Integer.valueOf(1))) {
            b.c(parcel, 1, fVar.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            b.a(parcel, 2, fVar.aKb, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            b.a(parcel, 3, fVar.mDescription, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            b.a(parcel, 4, fVar.aIW, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            b.a(parcel, 5, fVar.aFR, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            b.a(parcel, 6, fVar.mName, true);
        }
        if (set.contains(Integer.valueOf(7))) {
            b.a(parcel, 7, fVar.aKc);
        }
        if (set.contains(Integer.valueOf(8))) {
            b.a(parcel, 8, fVar.aJm, true);
        }
        if (set.contains(Integer.valueOf(9))) {
            b.a(parcel, 9, fVar.Yv, true);
        }
        if (set.contains(Integer.valueOf(10))) {
            b.c(parcel, 10, fVar.EB);
        }
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hf(x0);
    }

    public f hf(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        Set hashSet = new HashSet();
        String str2 = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str7 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str6 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str5 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str4 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str3 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    hashSet.add(Integer.valueOf(7));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str2 = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str = a.p(parcel, bS);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    i = a.g(parcel, bS);
                    hashSet.add(Integer.valueOf(10));
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new f(hashSet, i2, str7, str6, str5, str4, str3, z, str2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public f[] jQ(int i) {
        return new f[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jQ(x0);
    }
}
