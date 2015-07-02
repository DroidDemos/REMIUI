package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class tf implements Creator<te> {
    static void a(te teVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, teVar.versionCode);
        b.a(parcel, 2, teVar.packageName, false);
        b.c(parcel, 3, teVar.aHl);
        b.c(parcel, 4, teVar.aHm);
        b.a(parcel, 5, teVar.aHn, false);
        b.a(parcel, 6, teVar.aHo, false);
        b.a(parcel, 7, teVar.aHp);
        b.a(parcel, 8, teVar.aHq, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gT(x0);
    }

    public te gT(Parcel parcel) {
        int i = 0;
        String str = null;
        int bT = a.bT(parcel);
        boolean z = true;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        String str4 = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new te(i3, str4, i2, i, str3, str2, z, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public te[] jD(int i) {
        return new te[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jD(x0);
    }
}
