package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GlobalSearchApplicationInfo implements SafeParcelable {
    public static final n CREATOR;
    public final String defaultIntentAction;
    public final String defaultIntentActivity;
    public final String defaultIntentData;
    public final int iconId;
    public final int labelId;
    final int mVersionCode;
    final String packageName;
    public final int settingsDescriptionId;

    static {
        CREATOR = new n();
    }

    GlobalSearchApplicationInfo(int versionCode, String packageName, int labelId, int settingsDescriptionId, int iconId, String defaultIntentAction, String defaultIntentData, String defaultIntentActivity) {
        this.mVersionCode = versionCode;
        this.packageName = packageName;
        this.labelId = labelId;
        this.settingsDescriptionId = settingsDescriptionId;
        this.iconId = iconId;
        this.defaultIntentAction = defaultIntentAction;
        this.defaultIntentData = defaultIntentData;
        this.defaultIntentActivity = defaultIntentActivity;
    }

    public int describeContents() {
        n nVar = CREATOR;
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GlobalSearchApplicationInfo)) {
            return false;
        }
        GlobalSearchApplicationInfo globalSearchApplicationInfo = (GlobalSearchApplicationInfo) other;
        return TextUtils.equals(this.packageName, globalSearchApplicationInfo.packageName) && this.labelId == globalSearchApplicationInfo.labelId && this.settingsDescriptionId == globalSearchApplicationInfo.settingsDescriptionId && this.iconId == globalSearchApplicationInfo.iconId && TextUtils.equals(this.defaultIntentAction, globalSearchApplicationInfo.defaultIntentAction) && TextUtils.equals(this.defaultIntentData, globalSearchApplicationInfo.defaultIntentData) && TextUtils.equals(this.defaultIntentActivity, globalSearchApplicationInfo.defaultIntentActivity);
    }

    public String toString() {
        return getClass().getSimpleName() + "{" + this.packageName + ";labelId=" + Integer.toHexString(this.labelId) + ";settingsDescriptionId=" + Integer.toHexString(this.settingsDescriptionId) + ";iconId=" + Integer.toHexString(this.iconId) + ";defaultIntentAction=" + this.defaultIntentAction + ";defaultIntentData=" + this.defaultIntentData + ";defaultIntentActivity=" + this.defaultIntentActivity + "}";
    }

    public void writeToParcel(Parcel out, int flags) {
        n nVar = CREATOR;
        n.a(this, out, flags);
    }
}
