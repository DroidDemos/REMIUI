package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ar implements Creator<aq> {
    static void a(aq aqVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, aqVar.mVersionCode);
        b.a(parcel, 2, aqVar.ve(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iU(x0);
    }

    public aq iU(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iBinder = a.q(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new aq(i, iBinder);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public aq[] lU(int i) {
        return new aq[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lU(x0);
    }
}
