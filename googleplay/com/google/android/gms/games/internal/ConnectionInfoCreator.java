package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ConnectionInfoCreator implements Creator<ConnectionInfo> {
    static void a(ConnectionInfo connectionInfo, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, connectionInfo.mT(), false);
        b.c(parcel, 1000, connectionInfo.getVersionCode());
        b.c(parcel, 2, connectionInfo.mU());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eL(x0);
    }

    public ConnectionInfo eL(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
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
            return new ConnectionInfo(i2, str, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ConnectionInfo[] gD(int i) {
        return new ConnectionInfo[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gD(x0);
    }
}
