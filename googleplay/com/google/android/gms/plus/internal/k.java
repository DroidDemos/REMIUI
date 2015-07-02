package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<i> {
    static void a(i iVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, iVar.getAccountName(), false);
        b.c(parcel, 1000, iVar.getVersionCode());
        b.a(parcel, 2, iVar.rz(), false);
        b.a(parcel, 3, iVar.rA(), false);
        b.a(parcel, 4, iVar.rB(), false);
        b.a(parcel, 5, iVar.rC(), false);
        b.a(parcel, 6, iVar.rD(), false);
        b.a(parcel, 7, iVar.rE(), false);
        b.a(parcel, 8, iVar.rF(), false);
        b.a(parcel, 9, iVar.rG(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gV(x0);
    }

    public i gV(Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str5 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    strArr3 = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    strArr2 = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    plusCommonExtras = (PlusCommonExtras) a.a(parcel, bS, PlusCommonExtras.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new i(i, str5, strArr3, strArr2, strArr, str4, str3, str2, str, plusCommonExtras);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public i[] jG(int i) {
        return new i[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jG(x0);
    }
}
