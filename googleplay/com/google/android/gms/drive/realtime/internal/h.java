package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<EndCompoundOperationRequest> {
    static void a(EndCompoundOperationRequest endCompoundOperationRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, endCompoundOperationRequest.mVersionCode);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ds(x0);
    }

    public EndCompoundOperationRequest ds(Parcel parcel) {
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
            return new EndCompoundOperationRequest(i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public EndCompoundOperationRequest[] fb(int i) {
        return new EndCompoundOperationRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fb(x0);
    }
}
