package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class fn implements Creator<fm> {
    static void a(fm fmVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, fmVar.versionCode);
        b.a(parcel, 2, fmVar.tK, false);
        b.a(parcel, 3, fmVar.tL, i, false);
        b.a(parcel, 4, fmVar.lV, i, false);
        b.a(parcel, 5, fmVar.lO, false);
        b.a(parcel, 6, fmVar.applicationInfo, i, false);
        b.a(parcel, 7, fmVar.tM, i, false);
        b.a(parcel, 8, fmVar.tN, false);
        b.a(parcel, 9, fmVar.tO, false);
        b.a(parcel, 10, fmVar.tP, false);
        b.a(parcel, 11, fmVar.lR, i, false);
        b.a(parcel, 12, fmVar.tQ, false);
        b.c(parcel, 13, fmVar.tR);
        b.c(parcel, 14, fmVar.mf, false);
        b.a(parcel, 15, fmVar.tS, false);
        b.a(parcel, 16, fmVar.tT);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return h(x0);
    }

    public fm h(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Bundle bundle = null;
        ba baVar = null;
        bd bdVar = null;
        String str = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        gx gxVar = null;
        Bundle bundle2 = null;
        int i2 = 0;
        List list = null;
        Bundle bundle3 = null;
        boolean z = false;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    baVar = (ba) a.a(parcel, bS, ba.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bdVar = (bd) a.a(parcel, bS, bd.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    applicationInfo = (ApplicationInfo) a.a(parcel, bS, ApplicationInfo.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    packageInfo = (PackageInfo) a.a(parcel, bS, PackageInfo.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    gxVar = (gx) a.a(parcel, bS, gx.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    bundle2 = a.r(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    bundle3 = a.r(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new fm(i, bundle, baVar, bdVar, str, applicationInfo, packageInfo, str2, str3, str4, gxVar, bundle2, i2, list, bundle3, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return q(x0);
    }

    public fm[] q(int i) {
        return new fm[i];
    }
}
