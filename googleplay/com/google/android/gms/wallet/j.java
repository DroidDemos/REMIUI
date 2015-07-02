package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class j implements Creator<LoyaltyWalletObject> {
    static void a(LoyaltyWalletObject loyaltyWalletObject, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, loyaltyWalletObject.getVersionCode());
        b.a(parcel, 2, loyaltyWalletObject.fl, false);
        b.a(parcel, 3, loyaltyWalletObject.accountId, false);
        b.a(parcel, 4, loyaltyWalletObject.aTM, false);
        b.a(parcel, 5, loyaltyWalletObject.aTN, false);
        b.a(parcel, 6, loyaltyWalletObject.accountName, false);
        b.a(parcel, 7, loyaltyWalletObject.aTO, false);
        b.a(parcel, 8, loyaltyWalletObject.aTP, false);
        b.a(parcel, 9, loyaltyWalletObject.aTQ, false);
        b.a(parcel, 10, loyaltyWalletObject.aTR, false);
        b.a(parcel, 11, loyaltyWalletObject.aTS, false);
        b.c(parcel, 12, loyaltyWalletObject.state);
        b.d(parcel, 13, loyaltyWalletObject.aTT, false);
        b.a(parcel, 14, loyaltyWalletObject.aTU, i, false);
        b.d(parcel, 15, loyaltyWalletObject.aTV, false);
        b.a(parcel, 17, loyaltyWalletObject.aTX, false);
        b.a(parcel, 16, loyaltyWalletObject.aTW, false);
        b.a(parcel, 19, loyaltyWalletObject.aTZ);
        b.d(parcel, 18, loyaltyWalletObject.aTY, false);
        b.d(parcel, 21, loyaltyWalletObject.aUb, false);
        b.d(parcel, 20, loyaltyWalletObject.aUa, false);
        b.a(parcel, 23, loyaltyWalletObject.aUd, i, false);
        b.d(parcel, 22, loyaltyWalletObject.aUc, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return if(x0);
    }

    public LoyaltyWalletObject if(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        int i2 = 0;
        ArrayList ji = com.google.android.gms.common.util.a.ji();
        TimeInterval timeInterval = null;
        ArrayList ji2 = com.google.android.gms.common.util.a.ji();
        String str11 = null;
        String str12 = null;
        ArrayList ji3 = com.google.android.gms.common.util.a.ji();
        boolean z = false;
        ArrayList ji4 = com.google.android.gms.common.util.a.ji();
        ArrayList ji5 = com.google.android.gms.common.util.a.ji();
        ArrayList ji6 = com.google.android.gms.common.util.a.ji();
        LoyaltyPoints loyaltyPoints = null;
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
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str7 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str8 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str9 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    str10 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    ji = a.c(parcel, bS, WalletObjectMessage.CREATOR);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    timeInterval = (TimeInterval) a.a(parcel, bS, TimeInterval.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    ji2 = a.c(parcel, bS, LatLng.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str11 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    str12 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    ji3 = a.c(parcel, bS, LabelValueRow.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    z = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    ji4 = a.c(parcel, bS, UriData.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    ji5 = a.c(parcel, bS, TextModuleData.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    ji6 = a.c(parcel, bS, UriData.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    loyaltyPoints = (LoyaltyPoints) a.a(parcel, bS, LoyaltyPoints.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new LoyaltyWalletObject(i, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, i2, ji, timeInterval, ji2, str11, str12, ji3, z, ji4, ji5, ji6, loyaltyPoints);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public LoyaltyWalletObject[] la(int i) {
        return new LoyaltyWalletObject[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return la(x0);
    }
}
