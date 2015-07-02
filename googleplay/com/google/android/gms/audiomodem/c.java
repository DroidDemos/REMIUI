package com.google.android.gms.audiomodem;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class c implements Creator<DtmfEncoding> {
    static void a(DtmfEncoding dtmfEncoding, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, dtmfEncoding.getVersionCode());
        b.c(parcel, 2, dtmfEncoding.getTokenLengthBytes());
        b.a(parcel, 3, dtmfEncoding.shouldIncludeParitySymbol());
        b.a(parcel, 4, dtmfEncoding.getCoderSampleRate());
        b.c(parcel, 5, dtmfEncoding.getBasebandDecimationFactor());
        b.c(parcel, 6, dtmfEncoding.getWindowDurationMillis());
        b.c(parcel, 7, dtmfEncoding.getFrequenciesPerSymbol());
        b.c(parcel, 8, dtmfEncoding.getNumCrcCheckBytes());
        b.J(parcel, bU);
    }

    public DtmfEncoding V(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        float f = 0.0f;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i6 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new DtmfEncoding(i6, i5, z, f, i4, i3, i2, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public DtmfEncoding[] au(int i) {
        return new DtmfEncoding[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return V(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return au(x0);
    }
}
