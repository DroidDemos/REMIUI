package com.google.android.gms.feedback;

import android.app.ApplicationErrorReport;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;

public class FeedbackOptions implements SafeParcelable {
    public static final Creator<FeedbackOptions> CREATOR;
    public String mAccountInUse;
    public ApplicationErrorReport mApplicationErrorReport;
    public BitmapTeleporter mBitmapTeleporter;
    public String mCategoryTag;
    public String mDescription;
    public boolean mExcludePii;
    public ArrayList<FileTeleporter> mFileTeleporters;
    public String mPackageName;
    public String mPrimaryThemeColor;
    public Bundle mPsdBundle;
    public final int mVersionCode;

    static {
        CREATOR = new b();
    }

    private FeedbackOptions() {
        this(1, null, null, null, null, new ApplicationErrorReport(), null, null, null, null, true);
    }

    FeedbackOptions(int versionCode, String accountInUse, Bundle psdBundle, String primaryThemeColor, String description, ApplicationErrorReport applicationErrorReport, String categoryTag, BitmapTeleporter bitmapTeleporter, String packageName, ArrayList<FileTeleporter> fileTeleporters, boolean excludePii) {
        this.mVersionCode = versionCode;
        this.mAccountInUse = accountInUse;
        this.mPsdBundle = psdBundle;
        this.mPrimaryThemeColor = primaryThemeColor;
        this.mDescription = description;
        this.mApplicationErrorReport = applicationErrorReport;
        this.mCategoryTag = categoryTag;
        this.mBitmapTeleporter = bitmapTeleporter;
        this.mPackageName = packageName;
        this.mFileTeleporters = fileTeleporters;
        this.mExcludePii = excludePii;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
