package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<ErrorReport> {
    static void a(ErrorReport errorReport, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, errorReport.versionCode);
        b.a(parcel, 2, errorReport.applicationErrorReport, i, false);
        b.a(parcel, 3, errorReport.description, false);
        b.c(parcel, 4, errorReport.packageVersion);
        b.a(parcel, 5, errorReport.packageVersionName, false);
        b.a(parcel, 6, errorReport.device, false);
        b.a(parcel, 7, errorReport.buildId, false);
        b.a(parcel, 8, errorReport.buildType, false);
        b.a(parcel, 9, errorReport.model, false);
        b.a(parcel, 10, errorReport.product, false);
        b.a(parcel, 11, errorReport.buildFingerprint, false);
        b.c(parcel, 12, errorReport.sdk_int);
        b.a(parcel, 13, errorReport.release, false);
        b.a(parcel, 14, errorReport.incremental, false);
        b.a(parcel, 15, errorReport.codename, false);
        b.a(parcel, 17, errorReport.brand, false);
        b.a(parcel, 16, errorReport.board, false);
        b.a(parcel, 19, errorReport.systemLog, false);
        b.a(parcel, 18, errorReport.runningApplications, false);
        b.a(parcel, 21, errorReport.anrStackTraces, false);
        b.a(parcel, 20, errorReport.eventLog, false);
        b.a(parcel, 23, errorReport.screenshotBytes, false);
        b.a(parcel, 22, errorReport.screenshot, false);
        b.c(parcel, 25, errorReport.screenshotWidth);
        b.c(parcel, 24, errorReport.screenshotHeight);
        b.c(parcel, 27, errorReport.networkType);
        b.c(parcel, 26, errorReport.phoneType);
        b.a(parcel, 29, errorReport.account, false);
        b.a(parcel, 28, errorReport.networkName, false);
        b.a(parcel, 31, errorReport.psdBundle, false);
        b.a(parcel, 30, errorReport.localeString, false);
        b.c(parcel, 34, errorReport.networkMnc);
        b.a(parcel, 35, errorReport.isCtlAllowed);
        b.a(parcel, 32, errorReport.isSilentSend);
        b.c(parcel, 33, errorReport.networkMcc);
        b.c(parcel, 38, errorReport.throwLineNumber);
        b.a(parcel, 39, errorReport.throwClassName, false);
        b.a(parcel, 36, errorReport.exceptionClassName, false);
        b.a(parcel, 37, errorReport.throwFileName, false);
        b.a(parcel, 42, errorReport.exceptionMessage, false);
        b.a(parcel, 43, errorReport.categoryTag, false);
        b.a(parcel, 40, errorReport.throwMethodName, false);
        b.a(parcel, 41, errorReport.stackTrace, false);
        b.a(parcel, 46, errorReport.bitmapTeleporter, i, false);
        b.a(parcel, 47, errorReport.screenshotPath, false);
        b.a(parcel, 44, errorReport.color, false);
        b.a(parcel, 45, errorReport.submittingPackageName, false);
        b.a(parcel, 51, errorReport.launcher, false);
        b.a(parcel, 50, errorReport.excludePii);
        b.a(parcel, 49, errorReport.psdFilePaths, false);
        b.a(parcel, 48, errorReport.fileTeleporterList, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dF(x0);
    }

    public ErrorReport dF(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        ApplicationErrorReport applicationErrorReport = null;
        String str = null;
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        int i3 = 0;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str14 = null;
        String str15 = null;
        byte[] bArr = null;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        String str16 = null;
        String str17 = null;
        String str18 = null;
        Bundle bundle = null;
        boolean z = false;
        int i8 = 0;
        int i9 = 0;
        boolean z2 = false;
        String str19 = null;
        String str20 = null;
        int i10 = 0;
        String str21 = null;
        String str22 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        String str27 = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str28 = null;
        FileTeleporter[] fileTeleporterArr = null;
        String[] strArr4 = null;
        boolean z3 = false;
        String str29 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    applicationErrorReport = (ApplicationErrorReport) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, ApplicationErrorReport.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str4 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str5 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str6 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    str7 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    str8 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    i3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    str9 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    str10 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    str11 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str12 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    str13 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    strArr = com.google.android.gms.common.internal.safeparcel.a.B(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    strArr2 = com.google.android.gms.common.internal.safeparcel.a.B(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    strArr3 = com.google.android.gms.common.internal.safeparcel.a.B(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    str14 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    str15 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    bArr = com.google.android.gms.common.internal.safeparcel.a.s(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    i4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    i5 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    i6 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                    i7 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                    str16 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                    str17 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                    str18 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModePasteDrawable /*31*/:
                    bundle = com.google.android.gms.common.internal.safeparcel.a.r(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                    i8 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                    i9 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                    z2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                    str19 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_textAppearanceLargePopupMenu /*37*/:
                    str20 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_textAppearanceSmallPopupMenu /*38*/:
                    i10 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionDropDownStyle /*39*/:
                    str21 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                    str22 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_spinnerStyle /*41*/:
                    str23 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                    str24 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_homeAsUpIndicator /*43*/:
                    str25 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_actionButtonStyle /*44*/:
                    str26 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                    str27 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_buttonBarButtonStyle /*46*/:
                    bitmapTeleporter = (BitmapTeleporter) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, BitmapTeleporter.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Theme_selectableItemBackground /*47*/:
                    str28 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                    fileTeleporterArr = (FileTeleporter[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS, FileTeleporter.CREATOR);
                    break;
                case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                    strArr4 = com.google.android.gms.common.internal.safeparcel.a.B(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                    z3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
                    str29 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ErrorReport(i, applicationErrorReport, str, i2, str2, str3, str4, str5, str6, str7, str8, i3, str9, str10, str11, str12, str13, strArr, strArr2, strArr3, str14, str15, bArr, i4, i5, i6, i7, str16, str17, str18, bundle, z, i8, i9, z2, str19, str20, i10, str21, str22, str23, str24, str25, str26, str27, bitmapTeleporter, str28, fileTeleporterArr, strArr4, z3, str29);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public ErrorReport[] fs(int i) {
        return new ErrorReport[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fs(x0);
    }
}
