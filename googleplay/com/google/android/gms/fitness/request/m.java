package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;

public class m implements Creator<ListSubscriptionsRequest> {
    static void a(ListSubscriptionsRequest listSubscriptionsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, listSubscriptionsRequest.getDataType(), i, false);
        b.c(parcel, 1000, listSubscriptionsRequest.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eh(x0);
    }

    public ListSubscriptionsRequest eh(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DataType dataType = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataType = (DataType) a.a(parcel, bS, DataType.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ListSubscriptionsRequest(i, dataType);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ListSubscriptionsRequest[] fV(int i) {
        return new ListSubscriptionsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fV(x0);
    }
}
