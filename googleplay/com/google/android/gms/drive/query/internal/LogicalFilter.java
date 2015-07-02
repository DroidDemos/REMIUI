package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class LogicalFilter extends AbstractFilter {
    public static final Creator<LogicalFilter> CREATOR;
    final List<FilterHolder> acC;
    final Operator acp;
    final int mVersionCode;

    static {
        CREATOR = new i();
    }

    LogicalFilter(int versionCode, Operator operator, List<FilterHolder> filterHolders) {
        this.mVersionCode = versionCode;
        this.acp = operator;
        this.acC = filterHolders;
    }

    public <T> T a(f<T> fVar) {
        List arrayList = new ArrayList();
        for (FilterHolder filter : this.acC) {
            arrayList.add(filter.getFilter().a(fVar));
        }
        return fVar.b(this.acp, arrayList);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        i.a(this, out, flags);
    }
}
