package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<d> {
    static void a(d dVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, dVar.mVersionCode);
        b.a(parcel, 2, dVar.getLat(), false);
        b.a(parcel, 3, dVar.getLng(), false);
        b.a(parcel, 4, dVar.getName(), false);
        b.a(parcel, 5, dVar.getRadiusMeters(), false);
        b.a(parcel, 6, dVar.getLocationType(), false);
        b.a(parcel, 8, dVar.getDisplayAddress(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hl(x0);
    }

    public d hl(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        Integer num = null;
        Integer num2 = null;
        String str2 = null;
        Double d = null;
        Double d2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    d2 = a.n(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    d = a.n(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    num2 = a.h(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    num = a.h(parcel, bS);
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
            return new d(i, d2, d, str2, num2, num, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public d[] jW(int i) {
        return new d[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jW(x0);
    }
}
