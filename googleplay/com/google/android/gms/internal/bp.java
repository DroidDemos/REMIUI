package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class bp implements Creator<bo> {
    static void a(bo boVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, boVar.versionCode);
        b.c(parcel, 2, boVar.oS);
        b.c(parcel, 3, boVar.backgroundColor);
        b.c(parcel, 4, boVar.oT);
        b.c(parcel, 5, boVar.oU);
        b.c(parcel, 6, boVar.oV);
        b.c(parcel, 7, boVar.oW);
        b.c(parcel, 8, boVar.oX);
        b.c(parcel, 9, boVar.oY);
        b.a(parcel, 10, boVar.oZ, false);
        b.c(parcel, 11, boVar.pa);
        b.a(parcel, 12, boVar.pb, false);
        b.c(parcel, 13, boVar.pc);
        b.c(parcel, 14, boVar.pd);
        b.a(parcel, 15, boVar.query, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return d(x0);
    }

    public bo d(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        String str = null;
        int i10 = 0;
        String str2 = null;
        int i11 = 0;
        int i12 = 0;
        String str3 = null;
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
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i4 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i5 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i6 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    i7 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    i8 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    i9 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    i10 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    i11 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    i12 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    str3 = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new bo(i, i2, i3, i4, i5, i6, i7, i8, i9, str, i10, str2, i11, i12, str3);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public bo[] i(int i) {
        return new bo[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return i(x0);
    }
}
