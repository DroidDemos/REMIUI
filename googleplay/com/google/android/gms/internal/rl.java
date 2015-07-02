package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class rl implements Creator<rk> {
    static void a(rk rkVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, rkVar.qI());
        b.c(parcel, 1000, rkVar.getVersionCode());
        b.c(parcel, 2, rkVar.qJ());
        b.a(parcel, 3, rkVar.qK());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gJ(x0);
    }

    public rk gJ(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case 1000:
                    i3 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new rk(i3, i2, i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public rk[] jm(int i) {
        return new rk[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jm(x0);
    }
}
