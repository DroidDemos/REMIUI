package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class i implements Creator<LatLng> {
    static void a(LatLng latLng, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, latLng.getVersionCode());
        b.a(parcel, 2, latLng.latitude);
        b.a(parcel, 3, latLng.longitude);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gq(x0);
    }

    public LatLng gq(Parcel parcel) {
        double d = 0.0d;
        int bT = a.bT(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    d2 = a.m(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    d = a.m(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new LatLng(i, d2, d);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LatLng[] iP(int i) {
        return new LatLng[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iP(x0);
    }
}
