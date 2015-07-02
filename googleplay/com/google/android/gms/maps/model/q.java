package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class q implements Creator<StreetViewPanoramaCamera> {
    static void a(StreetViewPanoramaCamera streetViewPanoramaCamera, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, streetViewPanoramaCamera.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaCamera.zoom);
        b.a(parcel, 3, streetViewPanoramaCamera.tilt);
        b.a(parcel, 4, streetViewPanoramaCamera.bearing);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gu(x0);
    }

    public StreetViewPanoramaCamera gu(Parcel parcel) {
        float f = 0.0f;
        int bT = a.bT(parcel);
        float f2 = 0.0f;
        int i = 0;
        float f3 = 0.0f;
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
                    f3 = a.l(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    f = a.l(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new StreetViewPanoramaCamera(i, f2, f3, f);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StreetViewPanoramaCamera[] iT(int i) {
        return new StreetViewPanoramaCamera[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iT(x0);
    }
}
