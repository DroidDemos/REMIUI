package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.wearable.ConnectionConfiguration;
import com.google.android.wallet.instrumentmanager.R;

public class u implements Creator<t> {
    static void a(t tVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, tVar.versionCode);
        b.c(parcel, 2, tVar.statusCode);
        b.a(parcel, 3, tVar.aWP, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iL(x0);
    }

    public t iL(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        ConnectionConfiguration[] connectionConfigurationArr = null;
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
                    connectionConfigurationArr = (ConnectionConfiguration[]) a.b(parcel, bS, ConnectionConfiguration.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new t(i2, i, connectionConfigurationArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public t[] lL(int i) {
        return new t[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lL(x0);
    }
}
