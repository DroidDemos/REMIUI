package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class NotFilter extends AbstractFilter {
    public static final Creator<NotFilter> CREATOR;
    final FilterHolder acD;
    final int mVersionCode;

    static {
        CREATOR = new k();
    }

    NotFilter(int versionCode, FilterHolder toNegate) {
        this.mVersionCode = versionCode;
        this.acD = toNegate;
    }

    public <T> T a(f<T> fVar) {
        return fVar.l(this.acD.getFilter().a(fVar));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        k.a(this, out, flags);
    }
}
