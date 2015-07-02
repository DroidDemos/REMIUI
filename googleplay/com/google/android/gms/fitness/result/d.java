package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<DataTypeResult> {
    static void a(DataTypeResult dataTypeResult, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, dataTypeResult.getStatus(), i, false);
        b.c(parcel, 1000, dataTypeResult.getVersionCode());
        b.a(parcel, 3, dataTypeResult.getDataType(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ey(x0);
    }

    public DataTypeResult ey(Parcel parcel) {
        DataType dataType = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            DataType dataType2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    dataType2 = dataType;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    dataType2 = (DataType) a.a(parcel, bS, DataType.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    DataType dataType3 = dataType;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    dataType2 = dataType3;
                    break;
                default:
                    a.b(parcel, bS);
                    dataType2 = dataType;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            dataType = dataType2;
        }
        if (parcel.dataPosition() == bT) {
            return new DataTypeResult(i, status, dataType);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataTypeResult[] gn(int i) {
        return new DataTypeResult[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return gn(x0);
    }
}
