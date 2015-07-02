package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class y implements Creator<VisibleRegion> {
    static void a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, visibleRegion.getVersionCode());
        b.a(parcel, 2, visibleRegion.nearLeft, i, false);
        b.a(parcel, 3, visibleRegion.nearRight, i, false);
        b.a(parcel, 4, visibleRegion.farLeft, i, false);
        b.a(parcel, 5, visibleRegion.farRight, i, false);
        b.a(parcel, 6, visibleRegion.latLngBounds, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gA(x0);
    }

    public VisibleRegion gA(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int bT = a.bT(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    latLng4 = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    latLng3 = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    latLng2 = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    latLng = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    latLngBounds = (LatLngBounds) a.a(parcel, bS, LatLngBounds.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public VisibleRegion[] iZ(int i) {
        return new VisibleRegion[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iZ(x0);
    }
}
