package com.google.android.gms.googlehelp;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.view.View;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import java.util.ArrayList;
import java.util.List;

public final class GoogleHelp implements SafeParcelable {
    public static final Creator<GoogleHelp> CREATOR;
    private Bitmap afS;
    String arN;
    Account arO;
    boolean arP;
    boolean arQ;
    List<String> arR;
    @Deprecated
    Bundle arS;
    @Deprecated
    Bitmap arT;
    @Deprecated
    byte[] arU;
    @Deprecated
    int arV;
    @Deprecated
    int arW;
    String arX;
    Uri arY;
    List<OverflowMenuItem> arZ;
    int asa;
    List<OfflineSuggestion> asb;
    boolean asc;
    ErrorReport asd;
    Bundle mPsdBundle;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    GoogleHelp(int versionCode, String helpCenterContext, Account googleAccount, Bundle psdBundle, boolean searchEnabled, boolean metricsReportingEnabled, List<String> supportPhoneNumbers, Bundle feedbackPsdBundle, Bitmap backupScreenshot, byte[] screenshotBytes, int screenshotWidth, int screenshotHeight, String apiDebugOption, Uri fallbackSupportUri, List<OverflowMenuItem> overflowMenuItems, int helpActivityTheme, List<OfflineSuggestion> offlineSuggestions, boolean showContactCardFirst, ErrorReport feedbackErrorReport) {
        this.asd = new ErrorReport();
        this.mVersionCode = versionCode;
        this.arN = helpCenterContext;
        this.arO = googleAccount;
        this.mPsdBundle = psdBundle;
        this.arP = searchEnabled;
        this.arQ = metricsReportingEnabled;
        this.arR = supportPhoneNumbers;
        this.arS = feedbackPsdBundle;
        this.arT = backupScreenshot;
        this.arU = screenshotBytes;
        this.arV = screenshotWidth;
        this.arW = screenshotHeight;
        this.arX = apiDebugOption;
        this.arY = fallbackSupportUri;
        this.arZ = overflowMenuItems;
        this.asa = helpActivityTheme;
        this.asb = offlineSuggestions;
        this.asc = showContactCardFirst;
        this.asd = feedbackErrorReport;
    }

    public GoogleHelp(String helpcenterContext) {
        this(3, helpcenterContext, null, null, true, true, new ArrayList(), null, null, null, 0, 0, null, null, new ArrayList(), 0, new ArrayList(), false, new ErrorReport());
    }

    public static Bitmap getScreenshot(Activity activity) {
        try {
            View rootView = activity.getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            return rootView.getDrawingCache();
        } catch (Exception e) {
            return null;
        }
    }

    public Intent buildHelpIntent() {
        return new Intent("com.google.android.gms.googlehelp.HELP").setPackage("com.google.android.gms").putExtra("EXTRA_GOOGLE_HELP", this);
    }

    @Deprecated
    public Intent buildHelpIntent(Context context) {
        return buildHelpIntent();
    }

    public int describeContents() {
        return 0;
    }

    public Uri getFallbackSupportUri() {
        return this.arY;
    }

    public GoogleHelp setFallbackSupportUri(Uri fallbackSupportUri) {
        this.arY = fallbackSupportUri;
        return this;
    }

    public GoogleHelp setGoogleAccount(Account googleAccount) {
        this.arO = googleAccount;
        return this;
    }

    @Deprecated
    public GoogleHelp setScreenshot(Bitmap screenshot) {
        this.afS = screenshot;
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (this.afS != null) {
            this.asd.setScreenshot(this.afS);
        }
        a.a(this, out, flags);
    }
}
