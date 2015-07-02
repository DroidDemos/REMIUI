package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<CreateContentsRequest> {
    static void a(CreateContentsRequest createContentsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, createContentsRequest.mVersionCode);
        b.c(parcel, 2, createContentsRequest.Ya);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cu(x0);
    }

    public CreateContentsRequest cu(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = 536870912;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CreateContentsRequest(i, i2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CreateContentsRequest[] dX(int i) {
        return new CreateContentsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dX(x0);
    }
}
