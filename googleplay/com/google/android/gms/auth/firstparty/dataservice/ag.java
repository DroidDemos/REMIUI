package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.auth.firstparty.shared.ScopeDetail;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class ag implements Creator<TokenResponse> {
    static void a(TokenResponse tokenResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, tokenResponse.version);
        b.a(parcel, 2, tokenResponse.accountName, false);
        b.a(parcel, 3, tokenResponse.Gv, false);
        b.a(parcel, 4, tokenResponse.GP, false);
        b.a(parcel, 5, tokenResponse.HM, false);
        b.a(parcel, 6, tokenResponse.Gx, false);
        b.a(parcel, 7, tokenResponse.HN, false);
        b.a(parcel, 8, tokenResponse.firstName, false);
        b.a(parcel, 9, tokenResponse.lastName, false);
        b.a(parcel, 10, tokenResponse.HO);
        b.a(parcel, 11, tokenResponse.HP);
        b.a(parcel, 12, tokenResponse.HQ);
        b.a(parcel, 13, tokenResponse.HR);
        b.a(parcel, 14, tokenResponse.Gy, i, false);
        b.d(parcel, 15, tokenResponse.HS, false);
        b.a(parcel, 17, tokenResponse.Hu, false);
        b.a(parcel, 16, tokenResponse.Hz, false);
        b.a(parcel, 19, tokenResponse.HU);
        b.a(parcel, 18, tokenResponse.HT);
        b.c(parcel, 20, tokenResponse.title);
        b.J(parcel, bU);
    }

    public TokenResponse aL(Parcel parcel) {
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
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        CaptchaChallenge captchaChallenge = null;
        List arrayList = new ArrayList();
        String str9 = null;
        String str10 = null;
        boolean z5 = false;
        boolean z6 = false;
        int i2 = 0;
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
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    z4 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    captchaChallenge = (CaptchaChallenge) a.a(parcel, bS, CaptchaChallenge.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    arrayList = a.c(parcel, bS, ScopeDetail.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str9 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    str10 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    z5 = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    z6 = a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    i2 = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new TokenResponse(i, str, str2, str3, str4, str5, str6, str7, str8, z, z2, z3, z4, captchaChallenge, arrayList, str9, str10, z5, z6, i2);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public TokenResponse[] bp(int i) {
        return new TokenResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aL(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bp(x0);
    }
}
