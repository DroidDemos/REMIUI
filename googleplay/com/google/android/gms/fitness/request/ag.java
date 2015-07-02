package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ag implements Creator<af> {
    static void a(af afVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, afVar.getVersionCode());
        b.a(parcel, 2, afVar.getDeviceAddress(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return et(x0);
    }

    public af et(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
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
            return new af(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public af[] gi(int i) {
        return new af[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gi(x0);
    }
}
