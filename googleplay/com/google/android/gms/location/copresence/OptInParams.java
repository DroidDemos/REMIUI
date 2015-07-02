package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OptInParams implements SafeParcelable {
    public static final Creator<OptInParams> CREATOR;
    private final int aum;
    private final int aun;
    private final int auo;
    private final int aup;
    private final String auq;
    private final String aur;
    private final String mPackageName;
    private final int mVersionCode;

    static {
        CREATOR = new k();
    }

    OptInParams(int versionCode, String packageName, int appTitleResId, int appDetailResId, int introImageResId, int introLabelResId, String namedAclName, String featureId) {
        this.mVersionCode = versionCode;
        this.mPackageName = packageName;
        this.aum = appTitleResId;
        this.aun = appDetailResId;
        this.auo = introImageResId;
        this.aup = introLabelResId;
        this.auq = namedAclName;
        this.aur = featureId;
    }

    public int describeContents() {
        return 0;
    }

    public int getAppDetailResId() {
        return this.aun;
    }

    public int getAppTitleResId() {
        return this.aum;
    }

    public String getFeatureId() {
        return this.aur;
    }

    public int getIntroImageResId() {
        return this.auo;
    }

    public int getIntroLabelResId() {
        return this.aup;
    }

    public String getNamedAclName() {
        return this.auq;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
