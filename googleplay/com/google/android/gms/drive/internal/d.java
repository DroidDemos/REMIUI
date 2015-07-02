package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class d implements Creator<CancelPendingActionsRequest> {
    static void a(CancelPendingActionsRequest cancelPendingActionsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, cancelPendingActionsRequest.mVersionCode);
        b.c(parcel, 2, cancelPendingActionsRequest.YO, false);
        b.J(parcel, bU);
    }

    public CancelPendingActionsRequest cq(Parcel parcel) {
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
            return new CancelPendingActionsRequest(i, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cq(x0);
    }

    public CancelPendingActionsRequest[] dT(int i) {
        return new CancelPendingActionsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dT(x0);
    }
}
