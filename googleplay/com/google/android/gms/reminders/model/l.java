package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class l implements Creator<m> {
    static void a(m mVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, mVar.mVersionCode);
        b.a(parcel, 2, mVar.getHour(), false);
        b.a(parcel, 3, mVar.getMinute(), false);
        b.a(parcel, 4, mVar.getSecond(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hp(x0);
    }

    public m hp(Parcel parcel) {
        Integer num = null;
        int bT = a.bT(parcel);
        int i = 0;
        Integer num2 = null;
        Integer num3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    num3 = a.h(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    num2 = a.h(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    num = a.h(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new m(i, num3, num2, num);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public m[] ka(int i) {
        return new m[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ka(x0);
    }
}
