package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class fp implements Creator<fo> {
    static void a(fo foVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, foVar.versionCode);
        b.a(parcel, 2, foVar.sg, false);
        b.a(parcel, 3, foVar.tU, false);
        b.c(parcel, 4, foVar.qx, false);
        b.c(parcel, 5, foVar.errorCode);
        b.c(parcel, 6, foVar.qy, false);
        b.a(parcel, 7, foVar.tV);
        b.a(parcel, 8, foVar.tW);
        b.a(parcel, 9, foVar.tX);
        b.c(parcel, 10, foVar.tY, false);
        b.a(parcel, 11, foVar.qB);
        b.c(parcel, 12, foVar.orientation);
        b.a(parcel, 13, foVar.tZ, false);
        b.a(parcel, 14, foVar.ua);
        b.a(parcel, 15, foVar.ub, false);
        b.a(parcel, 19, foVar.ud, false);
        b.a(parcel, 18, foVar.uc);
        b.a(parcel, 21, foVar.ue, false);
        b.a(parcel, 23, foVar.ug);
        b.a(parcel, 22, foVar.uf);
        b.a(parcel, 25, foVar.uh);
        b.a(parcel, 24, foVar.tT);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return i(x0);
    }

    public fo i(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        List list = null;
        int i2 = 0;
        List list2 = null;
        long j = 0;
        boolean z = false;
        long j2 = 0;
        List list3 = null;
        long j3 = 0;
        int i3 = 0;
        String str3 = null;
        long j4 = 0;
        String str4 = null;
        boolean z2 = false;
        String str5 = null;
        String str6 = null;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
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
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    list2 = a.E(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    j = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    list3 = a.E(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    j3 = a.i(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    j4 = a.i(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    str4 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    z2 = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    str5 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    str6 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    z3 = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    z4 = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    z5 = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    z6 = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new fo(i, str, str2, list, i2, list2, j, z, j2, list3, j3, i3, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return r(x0);
    }

    public fo[] r(int i) {
        return new fo[i];
    }
}
