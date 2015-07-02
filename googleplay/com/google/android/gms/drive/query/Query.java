package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import java.util.List;
import java.util.Locale;

public class Query implements SafeParcelable {
    public static final Creator<Query> CREATOR;
    final LogicalFilter acg;
    final String ach;
    final SortOrder aci;
    final List<String> acj;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    Query(int versionCode, LogicalFilter clause, String pageToken, SortOrder sortOrder, List<String> requestedMetadataFields) {
        this.mVersionCode = versionCode;
        this.acg = clause;
        this.ach = pageToken;
        this.aci = sortOrder;
        this.acj = requestedMetadataFields;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "Query[%s,%s,PageToken=%s]", new Object[]{this.acg, this.aci, this.ach});
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
