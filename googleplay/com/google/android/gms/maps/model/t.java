package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class t implements Creator<StreetViewPanoramaOrientation> {
    static void a(StreetViewPanoramaOrientation streetViewPanoramaOrientation, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, streetViewPanoramaOrientation.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaOrientation.tilt);
        b.a(parcel, 3, streetViewPanoramaOrientation.bearing);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gx(x0);
    }

    public StreetViewPanoramaOrientation gx(Parcel parcel) {
        float f = 0.0f;
        int bT = a.bT(parcel);
        int i = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    f2 = a.l(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    f = a.l(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new StreetViewPanoramaOrientation(i, f2, f);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StreetViewPanoramaOrientation[] iW(int i) {
        return new StreetViewPanoramaOrientation[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iW(x0);
    }
}
