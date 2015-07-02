package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class r implements Creator<StreetViewPanoramaLink> {
    static void a(StreetViewPanoramaLink streetViewPanoramaLink, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, streetViewPanoramaLink.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaLink.panoId, false);
        b.a(parcel, 3, streetViewPanoramaLink.bearing);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gv(x0);
    }

    public StreetViewPanoramaLink gv(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        float f = 0.0f;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
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
            return new StreetViewPanoramaLink(i, str, f);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StreetViewPanoramaLink[] iU(int i) {
        return new StreetViewPanoramaLink[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iU(x0);
    }
}
