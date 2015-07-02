package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class a implements Creator<CommonWalletObject> {
    static void a(CommonWalletObject commonWalletObject, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, commonWalletObject.getVersionCode());
        b.a(parcel, 2, commonWalletObject.fl, false);
        b.a(parcel, 3, commonWalletObject.aTS, false);
        b.a(parcel, 4, commonWalletObject.name, false);
        b.a(parcel, 5, commonWalletObject.aTM, false);
        b.a(parcel, 6, commonWalletObject.aTO, false);
        b.a(parcel, 7, commonWalletObject.aTP, false);
        b.a(parcel, 8, commonWalletObject.aTQ, false);
        b.a(parcel, 9, commonWalletObject.aTR, false);
        b.c(parcel, 10, commonWalletObject.state);
        b.d(parcel, 11, commonWalletObject.aTT, false);
        b.a(parcel, 12, commonWalletObject.aTU, i, false);
        b.d(parcel, 13, commonWalletObject.aTV, false);
        b.a(parcel, 14, commonWalletObject.aTW, false);
        b.a(parcel, 15, commonWalletObject.aTX, false);
        b.a(parcel, 17, commonWalletObject.aTZ);
        b.d(parcel, 16, commonWalletObject.aTY, false);
        b.d(parcel, 19, commonWalletObject.aUb, false);
        b.d(parcel, 18, commonWalletObject.aUa, false);
        b.d(parcel, 20, commonWalletObject.aUc, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iu(x0);
    }

    public CommonWalletObject iu(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        int i2 = 0;
        ArrayList ji = com.google.android.gms.common.util.a.ji();
        TimeInterval timeInterval = null;
        ArrayList ji2 = com.google.android.gms.common.util.a.ji();
        String str9 = null;
        String str10 = null;
        ArrayList ji3 = com.google.android.gms.common.util.a.ji();
        boolean z = false;
        ArrayList ji4 = com.google.android.gms.common.util.a.ji();
        ArrayList ji5 = com.google.android.gms.common.util.a.ji();
        ArrayList ji6 = com.google.android.gms.common.util.a.ji();
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str4 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str5 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str6 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str7 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str8 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    ji = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, WalletObjectMessage.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    timeInterval = (TimeInterval) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, TimeInterval.CREATOR);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    ji2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, LatLng.CREATOR);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str9 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    str10 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    ji3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, LabelValueRow.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    ji4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, UriData.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    ji5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, TextModuleData.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    ji6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, UriData.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new CommonWalletObject(i, str, str2, str3, str4, str5, str6, str7, str8, i2, ji, timeInterval, ji2, str9, str10, ji3, z, ji4, ji5, ji6);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public CommonWalletObject[] lu(int i) {
        return new CommonWalletObject[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lu(x0);
    }
}
