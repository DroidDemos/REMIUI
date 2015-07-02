package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder implements SafeParcelable {
    public static final Creator<FilterHolder> CREATOR;
    private final Filter acA;
    final ComparisonFilter<?> act;
    final FieldOnlyFilter acu;
    final LogicalFilter acv;
    final NotFilter acw;
    final InFilter<?> acx;
    final MatchAllFilter acy;
    final HasFilter acz;
    final int mVersionCode;

    static {
        CREATOR = new d();
    }

    FilterHolder(int versionCode, ComparisonFilter<?> comparisonField, FieldOnlyFilter fieldOnlyFilter, LogicalFilter logicalFilter, NotFilter notFilter, InFilter<?> containsFilter, MatchAllFilter matchAllFilter, HasFilter<?> hasFilter) {
        this.mVersionCode = versionCode;
        this.act = comparisonField;
        this.acu = fieldOnlyFilter;
        this.acv = logicalFilter;
        this.acw = notFilter;
        this.acx = containsFilter;
        this.acy = matchAllFilter;
        this.acz = hasFilter;
        if (this.act != null) {
            this.acA = this.act;
        } else if (this.acu != null) {
            this.acA = this.acu;
        } else if (this.acv != null) {
            this.acA = this.acv;
        } else if (this.acw != null) {
            this.acA = this.acw;
        } else if (this.acx != null) {
            this.acA = this.acx;
        } else if (this.acy != null) {
            this.acA = this.acy;
        } else if (this.acz != null) {
            this.acA = this.acz;
        } else {
            throw new IllegalArgumentException("At least one filter must be set.");
        }
    }

    public int describeContents() {
        return 0;
    }

    public Filter getFilter() {
        return this.acA;
    }

    public String toString() {
        return String.format("FilterHolder[%s]", new Object[]{this.acA});
    }

    public void writeToParcel(Parcel out, int flags) {
        d.a(this, out, flags);
    }
}
