package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UsageInfo implements SafeParcelable {
    public static final aj CREATOR;
    final DocumentId EF;
    final long EG;
    final int EH;
    final DocumentContents EI;
    final int mVersionCode;
    public final String query;

    static {
        CREATOR = new aj();
    }

    UsageInfo(int versionCode, DocumentId documentId, long timestamp, int usageType, String query, DocumentContents document) {
        this.mVersionCode = versionCode;
        this.EF = documentId;
        this.EG = timestamp;
        this.EH = usageType;
        this.query = query;
        this.EI = document;
    }

    public int describeContents() {
        aj ajVar = CREATOR;
        return 0;
    }

    public String toString() {
        return String.format("UsageInfo[documentId=%s, timestamp=%d, usageType=%d]", new Object[]{this.EF, Long.valueOf(this.EG), Integer.valueOf(this.EH)});
    }

    public void writeToParcel(Parcel dest, int flags) {
        aj ajVar = CREATOR;
        aj.a(this, dest, flags);
    }
}
