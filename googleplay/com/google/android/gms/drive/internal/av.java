package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class av implements Creator<OnResourceIdSetResponse> {
    static void a(OnResourceIdSetResponse onResourceIdSetResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onResourceIdSetResponse.getVersionCode());
        b.c(parcel, 2, onResourceIdSetResponse.jQ(), false);
        b.J(parcel, bU);
    }

    public OnResourceIdSetResponse cP(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = a.E(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnResourceIdSetResponse(i, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cP(x0);
    }

    public OnResourceIdSetResponse[] ex(int i) {
        return new OnResourceIdSetResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ex(x0);
    }
}
