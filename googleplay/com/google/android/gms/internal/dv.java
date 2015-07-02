package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class dv implements Creator<dw> {
    static void a(dw dwVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, dwVar.versionCode);
        b.a(parcel, 2, dwVar.sb, i, false);
        b.a(parcel, 3, dwVar.ck(), false);
        b.a(parcel, 4, dwVar.cl(), false);
        b.a(parcel, 5, dwVar.cm(), false);
        b.a(parcel, 6, dwVar.cn(), false);
        b.a(parcel, 7, dwVar.sg, false);
        b.a(parcel, 8, dwVar.sh);
        b.a(parcel, 9, dwVar.si, false);
        b.a(parcel, 10, dwVar.cp(), false);
        b.c(parcel, 11, dwVar.orientation);
        b.c(parcel, 12, dwVar.sk);
        b.a(parcel, 13, dwVar.url, false);
        b.a(parcel, 14, dwVar.lR, i, false);
        b.a(parcel, 15, dwVar.co(), false);
        b.a(parcel, 17, dwVar.sn, i, false);
        b.a(parcel, 16, dwVar.sm, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return f(x0);
    }

    public dw f(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        dt dtVar = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        String str = null;
        boolean z = false;
        String str2 = null;
        IBinder iBinder5 = null;
        int i2 = 0;
        int i3 = 0;
        String str3 = null;
        gx gxVar = null;
        IBinder iBinder6 = null;
        String str4 = null;
        ad adVar = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    dtVar = (dt) a.a(parcel, bS, dt.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    iBinder = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    iBinder2 = a.q(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    iBinder3 = a.q(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    iBinder4 = a.q(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    iBinder5 = a.q(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i3 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    gxVar = (gx) a.a(parcel, bS, gx.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    iBinder6 = a.q(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str4 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    adVar = (ad) a.a(parcel, bS, ad.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new dw(i, dtVar, iBinder, iBinder2, iBinder3, iBinder4, str, z, str2, iBinder5, i2, i3, str3, gxVar, iBinder6, str4, adVar);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public dw[] n(int i) {
        return new dw[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return n(x0);
    }
}
