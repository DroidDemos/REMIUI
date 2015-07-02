package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<e> {
    static void a(e eVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, eVar.lC(), i, false);
        b.c(parcel, 1000, eVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ec(x0);
    }

    public e ec(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DataSet dataSet = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataSet = (DataSet) a.a(parcel, bS, DataSet.CREATOR);
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
            return new e(i, dataSet);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public e[] fQ(int i) {
        return new e[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fQ(x0);
    }
}
