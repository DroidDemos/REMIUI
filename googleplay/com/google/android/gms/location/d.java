package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<LocationStatus> {
    static void a(LocationStatus locationStatus, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, locationStatus.atE);
        b.c(parcel, 1000, locationStatus.getVersionCode());
        b.c(parcel, 2, locationStatus.atF);
        b.a(parcel, 3, locationStatus.atG);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fk(x0);
    }

    public LocationStatus fk(Parcel parcel) {
        int i = 1;
        int bT = a.bT(parcel);
        int i2 = 0;
        long j = 0;
        int i3 = 1;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new LocationStatus(i2, i3, i, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LocationStatus[] hz(int i) {
        return new LocationStatus[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hz(x0);
    }
}
