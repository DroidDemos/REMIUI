package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class pi implements Creator<ph> {
    static void a(ph phVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, phVar.getRequestId(), false);
        b.c(parcel, 1000, phVar.getVersionCode());
        b.a(parcel, 2, phVar.getExpirationTime());
        b.a(parcel, 3, phVar.py());
        b.a(parcel, 4, phVar.getLatitude());
        b.a(parcel, 5, phVar.getLongitude());
        b.a(parcel, 6, phVar.pz());
        b.c(parcel, 7, phVar.getTransitionTypes());
        b.c(parcel, 8, phVar.getNotificationResponsiveness());
        b.c(parcel, 9, phVar.pA());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fJ(x0);
    }

    public ph fJ(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    s = a.f(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    d = a.m(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    d2 = a.m(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    f = a.l(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    i4 = a.g(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ph(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public ph[] ic(int i) {
        return new ph[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ic(x0);
    }
}
