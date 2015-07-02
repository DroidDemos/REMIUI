package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<DsssEncoding> {
    static void a(DsssEncoding dsssEncoding, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, dsssEncoding.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, dsssEncoding.getTokenLengthBytes());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, dsssEncoding.shouldIncludeParitySymbol());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, dsssEncoding.shouldUseSingleSideband());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, dsssEncoding.getNumberOfTapsLfsr());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, dsssEncoding.getCodeNumber());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, dsssEncoding.getCoderSampleRate());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, dsssEncoding.getUpsamplingFactor());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, dsssEncoding.getDesiredCarrierFrequency());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 10, dsssEncoding.getBitsPerSymbol());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 11, dsssEncoding.getMinCyclesPerFrame());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 12, dsssEncoding.getBasebandDecimationFactor());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 13, dsssEncoding.getNumCrcCheckBytes());
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public DsssEncoding U(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        float f = 0.0f;
        int i5 = 0;
        float f2 = 0.0f;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    f2 = a.l(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    i6 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    i7 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i8 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    i9 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new DsssEncoding(i, i2, z, z2, i3, i4, f, i5, f2, i6, i7, i8, i9);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DsssEncoding[] at(int i) {
        return new DsssEncoding[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return U(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return at(x0);
    }
}
