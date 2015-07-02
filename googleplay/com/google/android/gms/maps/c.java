package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<StreetViewPanoramaOptions> {
    static void a(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, streetViewPanoramaOptions.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaOptions.getStreetViewPanoramaCamera(), i, false);
        b.a(parcel, 3, streetViewPanoramaOptions.getPanoramaId(), false);
        b.a(parcel, 4, streetViewPanoramaOptions.getPosition(), i, false);
        b.a(parcel, 5, streetViewPanoramaOptions.getRadius(), false);
        b.a(parcel, 6, streetViewPanoramaOptions.qg());
        b.a(parcel, 7, streetViewPanoramaOptions.pW());
        b.a(parcel, 8, streetViewPanoramaOptions.qh());
        b.a(parcel, 9, streetViewPanoramaOptions.qi());
        b.a(parcel, 10, streetViewPanoramaOptions.pS());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gk(x0);
    }

    public StreetViewPanoramaOptions gk(Parcel parcel) {
        Integer num = null;
        byte b = (byte) 0;
        int bT = a.bT(parcel);
        byte b2 = (byte) 0;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) a.a(parcel, bS, StreetViewPanoramaCamera.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    latLng = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    num = a.h(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    b5 = a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    b4 = a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    b3 = a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    b2 = a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    b = a.e(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new StreetViewPanoramaOptions(i, streetViewPanoramaCamera, str, latLng, num, b5, b4, b3, b2, b);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public StreetViewPanoramaOptions[] iJ(int i) {
        return new StreetViewPanoramaOptions[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iJ(x0);
    }
}
