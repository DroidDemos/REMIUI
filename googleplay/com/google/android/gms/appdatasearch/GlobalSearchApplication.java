package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GlobalSearchApplication implements SafeParcelable {
    public static final m CREATOR;
    final k[] De;
    public final GlobalSearchApplicationInfo appInfo;
    public final boolean enabled;
    final int mVersionCode;

    static {
        CREATOR = new m();
    }

    GlobalSearchApplication(int versionCode, GlobalSearchApplicationInfo appInfo, k[] corpusFeatures, boolean enabled) {
        this.mVersionCode = versionCode;
        this.appInfo = appInfo;
        this.De = corpusFeatures;
        this.enabled = enabled;
    }

    public int describeContents() {
        m mVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        m mVar = CREATOR;
        m.a(this, out, flags);
    }
}
