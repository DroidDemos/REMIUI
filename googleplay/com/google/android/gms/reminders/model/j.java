package com.google.android.gms.reminders.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class j implements Creator<k> {
    static void a(k kVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, kVar.mVersionCode);
        b.a(parcel, 2, kVar.getSystemListId(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ho(x0);
    }

    public k ho(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Integer num = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    num = a.h(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new k(i, num);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public k[] jZ(int i) {
        return new k[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jZ(x0);
    }
}
