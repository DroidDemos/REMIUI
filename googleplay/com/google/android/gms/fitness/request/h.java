package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class h implements Creator<DataSourcesRequest> {
    static void a(DataSourcesRequest dataSourcesRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, dataSourcesRequest.getDataTypes(), false);
        b.c(parcel, 1000, dataSourcesRequest.getVersionCode());
        b.a(parcel, 2, dataSourcesRequest.lS(), false);
        b.a(parcel, 3, dataSourcesRequest.lR());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ee(x0);
    }

    public DataSourcesRequest ee(Parcel parcel) {
        List list = null;
        boolean z = false;
        int bT = a.bT(parcel);
        List list2 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list2 = a.c(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = a.C(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
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
            return new DataSourcesRequest(i, list2, list, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataSourcesRequest[] fS(int i) {
        return new DataSourcesRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fS(x0);
    }
}
