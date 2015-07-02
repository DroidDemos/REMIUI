package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ax implements Creator<OnSyncMoreResponse> {
    static void a(OnSyncMoreResponse onSyncMoreResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onSyncMoreResponse.mVersionCode);
        b.a(parcel, 2, onSyncMoreResponse.Zy);
        b.J(parcel, bU);
    }

    public OnSyncMoreResponse cR(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnSyncMoreResponse(i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cR(x0);
    }

    public OnSyncMoreResponse[] ez(int i) {
        return new OnSyncMoreResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ez(x0);
    }
}
