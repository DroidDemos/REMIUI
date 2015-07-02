package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<LatLngBounds> {
    static void a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, latLngBounds.getVersionCode());
        b.a(parcel, 2, latLngBounds.southwest, i, false);
        b.a(parcel, 3, latLngBounds.northeast, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gp(x0);
    }

    public LatLngBounds gp(Parcel parcel) {
        LatLng latLng = null;
        int bT = a.bT(parcel);
        int i = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < bT) {
            int g;
            LatLng latLng3;
            int bS = a.bS(parcel);
            LatLng latLng4;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    latLng4 = latLng;
                    latLng = latLng2;
                    g = a.g(parcel, bS);
                    latLng3 = latLng4;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    latLng4 = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    latLng3 = latLng;
                    latLng = latLng4;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    latLng3 = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    latLng = latLng2;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    latLng3 = latLng;
                    latLng = latLng2;
                    g = i;
                    break;
            }
            i = g;
            latLng2 = latLng;
            latLng = latLng3;
        }
        if (parcel.dataPosition() == bT) {
            return new LatLngBounds(i, latLng2, latLng);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LatLngBounds[] iO(int i) {
        return new LatLngBounds[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iO(x0);
    }
}
