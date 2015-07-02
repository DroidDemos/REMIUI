package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class t implements Creator<GoogleAccountSetupRequest> {
    static void a(GoogleAccountSetupRequest googleAccountSetupRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, googleAccountSetupRequest.version);
        b.a(parcel, 2, googleAccountSetupRequest.Ho, false);
        b.a(parcel, 3, googleAccountSetupRequest.Hp);
        b.a(parcel, 4, googleAccountSetupRequest.Hq);
        b.a(parcel, 5, googleAccountSetupRequest.Hr);
        b.a(parcel, 6, googleAccountSetupRequest.firstName, false);
        b.a(parcel, 7, googleAccountSetupRequest.lastName, false);
        b.a(parcel, 8, googleAccountSetupRequest.secondaryEmail, false);
        b.a(parcel, 9, googleAccountSetupRequest.Hs, false);
        b.a(parcel, 10, googleAccountSetupRequest.GM);
        b.a(parcel, 11, googleAccountSetupRequest.Ht);
        b.a(parcel, 12, googleAccountSetupRequest.GN);
        b.a(parcel, 13, googleAccountSetupRequest.Hu, false);
        b.a(parcel, 14, googleAccountSetupRequest.callingAppDescription, i, false);
        b.a(parcel, 15, googleAccountSetupRequest.GO, i, false);
        b.a(parcel, 17, googleAccountSetupRequest.phoneNumber, false);
        b.a(parcel, 16, googleAccountSetupRequest.Gu, i, false);
        b.a(parcel, 18, googleAccountSetupRequest.phoneCountryCode, false);
        b.J(parcel, bU);
    }

    public GoogleAccountSetupRequest az(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Bundle bundle = new Bundle();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        String str5 = null;
        AppDescription appDescription = null;
        AccountCredentials accountCredentials = null;
        CaptchaSolution captchaSolution = null;
        String str6 = null;
        String str7 = null;
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
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    z4 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    z5 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    z6 = a.c(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    accountCredentials = (AccountCredentials) a.a(parcel, bS, AccountCredentials.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    captchaSolution = (CaptchaSolution) a.a(parcel, bS, CaptchaSolution.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    str6 = a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    str7 = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GoogleAccountSetupRequest(i, bundle, z, z2, z3, str, str2, str3, str4, z4, z5, z6, str5, appDescription, accountCredentials, captchaSolution, str6, str7);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GoogleAccountSetupRequest[] bd(int i) {
        return new GoogleAccountSetupRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return az(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bd(x0);
    }
}
