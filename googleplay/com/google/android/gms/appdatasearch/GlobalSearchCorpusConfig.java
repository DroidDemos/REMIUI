package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GlobalSearchCorpusConfig implements SafeParcelable {
    public static final o CREATOR;
    public final Feature[] features;
    public final int[] globalSearchSectionMappings;
    final int mVersionCode;

    static {
        CREATOR = new o();
    }

    GlobalSearchCorpusConfig(int versionCode, int[] globalSearchSectionMappings, Feature[] features) {
        this.mVersionCode = versionCode;
        this.globalSearchSectionMappings = globalSearchSectionMappings;
        this.features = features;
    }

    public int describeContents() {
        o oVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        o oVar = CREATOR;
        o.a(this, out, flags);
    }
}
