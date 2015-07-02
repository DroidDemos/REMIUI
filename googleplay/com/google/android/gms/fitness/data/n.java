package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class n implements Creator<RawDataSet> {
    static void a(RawDataSet rawDataSet, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, rawDataSet.agR);
        b.c(parcel, 1000, rawDataSet.mVersionCode);
        b.c(parcel, 2, rawDataSet.agT);
        b.d(parcel, 3, rawDataSet.agU, false);
        b.a(parcel, 4, rawDataSet.agn);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dT(x0);
    }

    public RawDataSet dT(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        List list = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list = a.c(parcel, bS, RawDataPoint.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                case 1000:
                    i3 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RawDataSet(i3, i2, i, list, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RawDataSet[] fH(int i) {
        return new RawDataSet[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fH(x0);
    }
}
