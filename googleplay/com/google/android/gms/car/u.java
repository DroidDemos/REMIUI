package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class u implements Creator<CarUiInfo> {
    static void a(CarUiInfo carUiInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, carUiInfo.hasRotaryController());
        b.c(parcel, 1000, carUiInfo.getVersionCode());
        b.a(parcel, 2, carUiInfo.hasTouchscreen());
        b.J(parcel, bU);
    }

    public CarUiInfo bz(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
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
            return new CarUiInfo(i, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CarUiInfo[] cp(int i) {
        return new CarUiInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bz(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return cp(x0);
    }
}
