package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.google.android.gms.auth.firstparty.shared.PACLConfig;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class af implements Creator<TokenRequest> {
    static void a(TokenRequest tokenRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, tokenRequest.version);
        b.a(parcel, 2, tokenRequest.HE, false);
        b.a(parcel, 3, tokenRequest.accountName, false);
        b.a(parcel, 4, tokenRequest.Ho, false);
        b.a(parcel, 5, tokenRequest.HF, i, false);
        b.a(parcel, 6, tokenRequest.HG, i, false);
        b.a(parcel, 7, tokenRequest.Ht);
        b.a(parcel, 8, tokenRequest.GM);
        b.a(parcel, 9, tokenRequest.HH, false);
        b.a(parcel, 10, tokenRequest.callingAppDescription, i, false);
        b.a(parcel, 11, tokenRequest.Gu, i, false);
        b.a(parcel, 12, tokenRequest.HI);
        b.a(parcel, 13, tokenRequest.HJ);
        b.a(parcel, 14, tokenRequest.HK);
        b.J(parcel, bU);
    }

    public TokenRequest aK(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        Bundle bundle = new Bundle();
        FACLConfig fACLConfig = null;
        PACLConfig pACLConfig = null;
        boolean z = false;
        boolean z2 = false;
        String str3 = "com.google.android.gms.auth.firstparty.shared.Consent.UNKNOWN.toString()";
        AppDescription appDescription = null;
        CaptchaSolution captchaSolution = null;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
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
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    fACLConfig = (FACLConfig) a.a(parcel, bS, FACLConfig.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    pACLConfig = (PACLConfig) a.a(parcel, bS, PACLConfig.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    captchaSolution = (CaptchaSolution) a.a(parcel, bS, CaptchaSolution.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    z4 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    z5 = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new TokenRequest(i, str, str2, bundle, fACLConfig, pACLConfig, z, z2, str3, appDescription, captchaSolution, z3, z4, z5);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public TokenRequest[] bo(int i) {
        return new TokenRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aK(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bo(x0);
    }
}
