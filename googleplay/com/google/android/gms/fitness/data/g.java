package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class g implements Creator<DataType> {
    static void a(DataType dataType, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, dataType.getName(), false);
        b.c(parcel, 1000, dataType.getVersionCode());
        b.d(parcel, 2, dataType.getFields(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dO(x0);
    }

    public DataType dO(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = a.c(parcel, bS, Field.CREATOR);
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
            return new DataType(i, str, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DataType[] fC(int i) {
        return new DataType[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fC(x0);
    }
}
