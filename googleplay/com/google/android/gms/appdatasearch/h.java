package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<Feature> {
    static void a(Feature feature, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, feature.id);
        b.c(parcel, 1000, feature.mVersionCode);
        b.a(parcel, 2, feature.Db, false);
        b.J(parcel, bU);
    }

    public Feature[] M(int i) {
        return new Feature[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return s(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return M(x0);
    }

    public Feature s(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle = a.r(parcel, bS);
                    break;
                case 1000:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new Feature(i2, i, bundle);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
