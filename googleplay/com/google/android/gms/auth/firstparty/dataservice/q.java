package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class q implements Creator<ClearTokenResponse> {
    static void a(ClearTokenResponse clearTokenResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, clearTokenResponse.version);
        b.a(parcel, 2, clearTokenResponse.Gv, false);
        b.J(parcel, bU);
    }

    public ClearTokenResponse aw(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ClearTokenResponse(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ClearTokenResponse[] ba(int i) {
        return new ClearTokenResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aw(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ba(x0);
    }
}
