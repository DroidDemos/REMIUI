package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ad implements Creator<ac> {
    static void a(ac acVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, acVar.mf(), false);
        b.c(parcel, 1000, acVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return er(x0);
    }

    public ac er(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    iBinder = a.q(parcel, bS);
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
            return new ac(i, iBinder);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ac[] gg(int i) {
        return new ac[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gg(x0);
    }
}
