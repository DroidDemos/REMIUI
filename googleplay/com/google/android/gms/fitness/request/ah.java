package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;

public class ah implements Creator<UnsubscribeRequest> {
    static void a(UnsubscribeRequest unsubscribeRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, unsubscribeRequest.getDataType(), i, false);
        b.c(parcel, 1000, unsubscribeRequest.getVersionCode());
        b.a(parcel, 2, unsubscribeRequest.getDataSource(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eu(x0);
    }

    public UnsubscribeRequest eu(Parcel parcel) {
        DataSource dataSource = null;
        int bT = a.bT(parcel);
        int i = 0;
        DataType dataType = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            DataSource dataSource2;
            DataType dataType2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    DataType dataType3 = (DataType) a.a(parcel, bS, DataType.CREATOR);
                    dataSource2 = dataSource;
                    dataType2 = dataType3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    dataSource2 = (DataSource) a.a(parcel, bS, DataSource.CREATOR);
                    dataType2 = dataType;
                    i2 = i;
                    break;
                case 1000:
                    DataSource dataSource3 = dataSource;
                    dataType2 = dataType;
                    i2 = a.g(parcel, bS);
                    dataSource2 = dataSource3;
                    break;
                default:
                    a.b(parcel, bS);
                    dataSource2 = dataSource;
                    dataType2 = dataType;
                    i2 = i;
                    break;
            }
            i = i2;
            dataType = dataType2;
            dataSource = dataSource2;
        }
        if (parcel.dataPosition() == bT) {
            return new UnsubscribeRequest(i, dataType, dataSource);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UnsubscribeRequest[] gj(int i) {
        return new UnsubscribeRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gj(x0);
    }
}
