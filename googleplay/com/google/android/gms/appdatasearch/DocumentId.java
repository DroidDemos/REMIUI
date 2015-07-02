package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DocumentId implements SafeParcelable {
    public static final e CREATOR;
    final String CT;
    final String CU;
    final String mPackageName;
    final int mVersionCode;

    static {
        CREATOR = new e();
    }

    DocumentId(int versionCode, String packageName, String corpusName, String uri) {
        this.mVersionCode = versionCode;
        this.mPackageName = packageName;
        this.CT = corpusName;
        this.CU = uri;
    }

    public int describeContents() {
        e eVar = CREATOR;
        return 0;
    }

    public String toString() {
        return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", new Object[]{this.mPackageName, this.CT, this.CU});
    }

    public void writeToParcel(Parcel dest, int flags) {
        e eVar = CREATOR;
        e.a(this, dest, flags);
    }
}
