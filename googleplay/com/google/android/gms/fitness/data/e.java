package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class e implements Creator<DataSet> {
    static void a(DataSet dataSet, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, dataSet.getDataSource(), i, false);
        b.c(parcel, 1000, dataSet.getVersionCode());
        b.a(parcel, 2, dataSet.getDataType(), i, false);
        b.f(parcel, 3, dataSet.ls(), false);
        b.d(parcel, 4, dataSet.lt(), false);
        b.a(parcel, 5, dataSet.serverHasMoreData());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dM(x0);
    }

    public DataSet dM(Parcel parcel) {
        boolean z = false;
        List list = null;
        int bT = a.bT(parcel);
        List arrayList = new ArrayList();
        DataType dataType = null;
        DataSource dataSource = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataSource = (DataSource) a.a(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    dataType = (DataType) a.a(parcel, bS, DataType.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    a.a(parcel, bS, arrayList, getClass().getClassLoader());
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.c(parcel, bS, DataSource.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
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
            return new DataSet(i, dataSource, dataType, arrayList, list, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataSet[] fA(int i) {
        return new DataSet[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fA(x0);
    }
}
