package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class b implements Creator<FeedbackOptions> {
    static void a(FeedbackOptions feedbackOptions, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, feedbackOptions.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, feedbackOptions.mAccountInUse, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, feedbackOptions.mPsdBundle, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, feedbackOptions.mPrimaryThemeColor, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, feedbackOptions.mDescription, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, feedbackOptions.mApplicationErrorReport, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, feedbackOptions.mCategoryTag, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, feedbackOptions.mBitmapTeleporter, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, feedbackOptions.mPackageName, false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 10, feedbackOptions.mFileTeleporters, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, feedbackOptions.mExcludePii);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return dG(x0);
    }

    public FeedbackOptions dG(Parcel parcel) {
        boolean z = false;
        ArrayList arrayList = null;
        int bT = a.bT(parcel);
        String str = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str2 = null;
        ApplicationErrorReport applicationErrorReport = null;
        String str3 = null;
        String str4 = null;
        Bundle bundle = null;
        String str5 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bundle = a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    applicationErrorReport = (ApplicationErrorReport) a.a(parcel, bS, ApplicationErrorReport.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    bitmapTeleporter = (BitmapTeleporter) a.a(parcel, bS, BitmapTeleporter.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    arrayList = a.c(parcel, bS, FileTeleporter.CREATOR);
                    break;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new FeedbackOptions(i, str5, bundle, str4, str3, applicationErrorReport, str2, bitmapTeleporter, str, arrayList, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public FeedbackOptions[] ft(int i) {
        return new FeedbackOptions[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ft(x0);
    }
}
