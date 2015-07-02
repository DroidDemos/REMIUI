package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ds implements Creator<dt> {
    static void a(dt dtVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, dtVar.versionCode);
        b.a(parcel, 2, dtVar.rH, false);
        b.a(parcel, 3, dtVar.url, false);
        b.a(parcel, 4, dtVar.mimeType, false);
        b.a(parcel, 5, dtVar.packageName, false);
        b.a(parcel, 6, dtVar.rI, false);
        b.a(parcel, 7, dtVar.rJ, false);
        b.a(parcel, 8, dtVar.rK, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return e(x0);
    }

    public dt e(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str7 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str5 = a.p(parcel, bS);
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
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new dt(i, str7, str6, str5, str4, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public dt[] m(int i) {
        return new dt[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m(x0);
    }
}
