package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class FieldWithSortOrder implements SafeParcelable {
    public static final c CREATOR;
    final String abg;
    final boolean acs;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    FieldWithSortOrder(int versionCode, String fieldName, boolean isSortAscending) {
        this.mVersionCode = versionCode;
        this.abg = fieldName;
        this.acs = isSortAscending;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        Locale locale = Locale.US;
        String str = "FieldWithSortOrder[%s %s]";
        Object[] objArr = new Object[2];
        objArr[0] = this.abg;
        objArr[1] = this.acs ? "ASC" : "DESC";
        return String.format(locale, str, objArr);
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
