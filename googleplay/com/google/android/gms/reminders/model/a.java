package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, bVar.mVersionCode);
        b.a(parcel, 2, bVar.getYear(), false);
        b.a(parcel, 3, bVar.getMonth(), false);
        b.a(parcel, 4, bVar.getDay(), false);
        b.a(parcel, 5, bVar.getTime(), i, false);
        b.a(parcel, 6, bVar.getPeriod(), false);
        b.a(parcel, 8, bVar.getAbsoluteTimeMs(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hk(x0);
    }

    public b hk(Parcel parcel) {
        Long l = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        Integer num = null;
        m mVar = null;
        Integer num2 = null;
        Integer num3 = null;
        Integer num4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    num4 = com.google.android.gms.common.internal.safeparcel.a.h(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    num3 = com.google.android.gms.common.internal.safeparcel.a.h(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    num2 = com.google.android.gms.common.internal.safeparcel.a.h(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    mVar = (m) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, m.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    num = com.google.android.gms.common.internal.safeparcel.a.h(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    l = com.google.android.gms.common.internal.safeparcel.a.j(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(i, num4, num3, num2, mVar, num, l);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] jV(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jV(x0);
    }
}
