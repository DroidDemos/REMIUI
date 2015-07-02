package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class o implements Creator<DisconnectRequest> {
    static void a(DisconnectRequest disconnectRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, disconnectRequest.mVersionCode);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cz(x0);
    }

    public DisconnectRequest cz(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new DisconnectRequest(i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DisconnectRequest[] ed(int i) {
        return new DisconnectRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ed(x0);
    }
}
