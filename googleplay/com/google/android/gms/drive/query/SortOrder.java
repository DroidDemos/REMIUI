package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.List;
import java.util.Locale;

public class SortOrder implements SafeParcelable {
    public static final Creator<SortOrder> CREATOR;
    final List<FieldWithSortOrder> acn;
    final boolean aco;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    SortOrder(int versionCode, List<FieldWithSortOrder> sortingFields, boolean sortFolderFirst) {
        this.mVersionCode = versionCode;
        this.acn = sortingFields;
        this.aco = sortFolderFirst;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "SortOrder[%s, %s]", new Object[]{TextUtils.join(",", this.acn), Boolean.valueOf(this.aco)});
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
