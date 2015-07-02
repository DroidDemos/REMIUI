package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<GoogleMapOptions> {
    static void a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, googleMapOptions.getVersionCode());
        b.a(parcel, 2, googleMapOptions.pR());
        b.a(parcel, 3, googleMapOptions.pS());
        b.c(parcel, 4, googleMapOptions.getMapType());
        b.a(parcel, 5, googleMapOptions.getCamera(), i, false);
        b.a(parcel, 6, googleMapOptions.pT());
        b.a(parcel, 7, googleMapOptions.pU());
        b.a(parcel, 8, googleMapOptions.pV());
        b.a(parcel, 9, googleMapOptions.pW());
        b.a(parcel, 10, googleMapOptions.pX());
        b.a(parcel, 11, googleMapOptions.pY());
        b.a(parcel, 12, googleMapOptions.pZ());
        b.a(parcel, 14, googleMapOptions.qa());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gj(x0);
    }

    public GoogleMapOptions gj(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        byte b = (byte) 0;
        byte b2 = (byte) 0;
        int i2 = 0;
        CameraPosition cameraPosition = null;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        byte b6 = (byte) 0;
        byte b7 = (byte) 0;
        byte b8 = (byte) 0;
        byte b9 = (byte) 0;
        byte b10 = (byte) 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    b = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    b2 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    cameraPosition = (CameraPosition) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, CameraPosition.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    b3 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    b4 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    b5 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    b6 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    b7 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    b8 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    b9 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    b10 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GoogleMapOptions(i, b, b2, i2, cameraPosition, b3, b4, b5, b6, b7, b8, b9, b10);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public GoogleMapOptions[] iI(int i) {
        return new GoogleMapOptions[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iI(x0);
    }
}
