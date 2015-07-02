package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class s implements Creator<StreetViewPanoramaLocation> {
    static void a(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, streetViewPanoramaLocation.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaLocation.links, i, false);
        b.a(parcel, 3, streetViewPanoramaLocation.position, i, false);
        b.a(parcel, 4, streetViewPanoramaLocation.panoId, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gw(x0);
    }

    public StreetViewPanoramaLocation gw(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        LatLng latLng = null;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = null;
        while (parcel.dataPosition() < bT) {
            LatLng latLng2;
            StreetViewPanoramaLink[] streetViewPanoramaLinkArr2;
            int g;
            String str2;
            int bS = a.bS(parcel);
            String str3;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str3 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    g = a.g(parcel, bS);
                    str2 = str3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    g = i;
                    LatLng latLng3 = latLng;
                    streetViewPanoramaLinkArr2 = (StreetViewPanoramaLink[]) a.b(parcel, bS, StreetViewPanoramaLink.CREATOR);
                    str2 = str;
                    latLng2 = latLng3;
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    g = i;
                    str3 = str;
                    latLng2 = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    str2 = str3;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    g = i;
                    break;
                default:
                    a.b(parcel, bS);
                    str2 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    g = i;
                    break;
            }
            i = g;
            streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
            latLng = latLng2;
            str = str2;
        }
        if (parcel.dataPosition() == bT) {
            return new StreetViewPanoramaLocation(i, streetViewPanoramaLinkArr, latLng, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StreetViewPanoramaLocation[] iV(int i) {
        return new StreetViewPanoramaLocation[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iV(x0);
    }
}
