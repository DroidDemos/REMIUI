package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.wearable.ConnectionConfiguration;
import com.google.android.wallet.instrumentmanager.R;

public class s implements Creator<r> {
    static void a(r rVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, rVar.versionCode);
        b.c(parcel, 2, rVar.statusCode);
        b.a(parcel, 3, rVar.aWO, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iK(x0);
    }

    public r iK(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        ConnectionConfiguration connectionConfiguration = null;
        int i2 = 0;
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
                    connectionConfiguration = (ConnectionConfiguration) a.a(parcel, bS, ConnectionConfiguration.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new r(i2, i, connectionConfiguration);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public r[] lK(int i) {
        return new r[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lK(x0);
    }
}
