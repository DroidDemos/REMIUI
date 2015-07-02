package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<CircleOptions> {
    static void a(CircleOptions circleOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, circleOptions.getVersionCode());
        b.a(parcel, 2, circleOptions.getCenter(), i, false);
        b.a(parcel, 3, circleOptions.getRadius());
        b.a(parcel, 4, circleOptions.getStrokeWidth());
        b.c(parcel, 5, circleOptions.getStrokeColor());
        b.c(parcel, 6, circleOptions.getFillColor());
        b.a(parcel, 7, circleOptions.getZIndex());
        b.a(parcel, 8, circleOptions.isVisible());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gn(x0);
    }

    public CircleOptions gn(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int bT = a.bT(parcel);
        LatLng latLng = null;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    latLng = (LatLng) a.a(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    d = a.m(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    f2 = a.l(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CircleOptions(i3, latLng, d, f2, i2, i, f, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public CircleOptions[] iM(int i) {
        return new CircleOptions[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iM(x0);
    }
}
