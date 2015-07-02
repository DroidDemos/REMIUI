package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class nk implements Creator<nj> {
    static void a(nj njVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.d(parcel, 1, njVar.getDataTypes(), false);
        b.c(parcel, 1000, njVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dY(x0);
    }

    public nj dY(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    list = a.c(parcel, bS, DataType.CREATOR);
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
            return new nj(i, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public nj[] fM(int i) {
        return new nj[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fM(x0);
    }
}
