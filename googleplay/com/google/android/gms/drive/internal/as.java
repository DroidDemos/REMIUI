package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class as implements Creator<OnListParentsResponse> {
    static void a(OnListParentsResponse onListParentsResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onListParentsResponse.mVersionCode);
        b.a(parcel, 2, onListParentsResponse.aba, i, false);
        b.J(parcel, bU);
    }

    public OnListParentsResponse cM(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DataHolder dataHolder = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    dataHolder = (DataHolder) a.a(parcel, bS, DataHolder.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnListParentsResponse(i, dataHolder);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cM(x0);
    }

    public OnListParentsResponse[] eu(int i) {
        return new OnListParentsResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eu(x0);
    }
}
