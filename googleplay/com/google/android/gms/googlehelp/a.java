package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<GoogleHelp> {
    static void a(GoogleHelp googleHelp, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, googleHelp.mVersionCode);
        b.a(parcel, 2, googleHelp.arN, false);
        b.a(parcel, 3, googleHelp.arO, i, false);
        b.a(parcel, 4, googleHelp.mPsdBundle, false);
        b.a(parcel, 5, googleHelp.arP);
        b.a(parcel, 6, googleHelp.arQ);
        b.c(parcel, 7, googleHelp.arR, false);
        b.a(parcel, 10, googleHelp.arS, false);
        b.a(parcel, 11, googleHelp.arT, i, false);
        b.a(parcel, 14, googleHelp.arX, false);
        b.a(parcel, 15, googleHelp.arY, i, false);
        b.c(parcel, 17, googleHelp.asa);
        b.d(parcel, 16, googleHelp.arZ, false);
        b.a(parcel, 19, googleHelp.arU, false);
        b.d(parcel, 18, googleHelp.asb, false);
        b.c(parcel, 21, googleHelp.arW);
        b.c(parcel, 20, googleHelp.arV);
        b.a(parcel, 23, googleHelp.asd, i, false);
        b.a(parcel, 22, googleHelp.asc);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fb(x0);
    }

    public GoogleHelp fb(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        String str = null;
        Account account = null;
        Bundle bundle = null;
        boolean z = false;
        boolean z2 = false;
        List list = null;
        Bundle bundle2 = null;
        Bitmap bitmap = null;
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        String str2 = null;
        Uri uri = null;
        List list2 = null;
        int i4 = 0;
        List list3 = null;
        boolean z3 = false;
        ErrorReport errorReport = null;
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
                    account = (Account) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Account.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle = com.google.android.gms.common.internal.safeparcel.a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.E(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    bundle2 = com.google.android.gms.common.internal.safeparcel.a.r(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    bitmap = (Bitmap) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Bitmap.CREATOR);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    uri = (Uri) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Uri.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    list2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, OverflowMenuItem.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    i4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    list3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS, OfflineSuggestion.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    bArr = com.google.android.gms.common.internal.safeparcel.a.s(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    i3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    z3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    errorReport = (ErrorReport) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, ErrorReport.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GoogleHelp(i, str, account, bundle, z, z2, list, bundle2, bitmap, bArr, i2, i3, str2, uri, list2, i4, list3, z3, errorReport);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public GoogleHelp[] hj(int i) {
        return new GoogleHelp[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hj(x0);
    }
}
