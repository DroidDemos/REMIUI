package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.wallet.instrumentmanager.R;

public class nm implements Creator<nl> {
    static void a(nl nlVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, nlVar.getDataSource(), i, false);
        b.c(parcel, 1000, nlVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dZ(x0);
    }

    public nl dZ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        DataSource dataSource = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    dataSource = (DataSource) a.a(parcel, bS, DataSource.CREATOR);
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
            return new nl(i, dataSource);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public nl[] fN(int i) {
        return new nl[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fN(x0);
    }
}
