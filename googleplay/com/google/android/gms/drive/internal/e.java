package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class e implements Creator<CheckResourceIdsExistRequest> {
    static void a(CheckResourceIdsExistRequest checkResourceIdsExistRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, checkResourceIdsExistRequest.getVersionCode());
        b.c(parcel, 2, checkResourceIdsExistRequest.jQ(), false);
        b.J(parcel, bU);
    }

    public CheckResourceIdsExistRequest cr(Parcel parcel) {
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
            return new CheckResourceIdsExistRequest(i, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cr(x0);
    }

    public CheckResourceIdsExistRequest[] dU(int i) {
        return new CheckResourceIdsExistRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return dU(x0);
    }
}
