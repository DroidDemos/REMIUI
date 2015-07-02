package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class ComparisonFilter<T> extends AbstractFilter {
    public static final a CREATOR;
    final Operator acp;
    final MetadataBundle acq;
    final MetadataField<T> acr;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    ComparisonFilter(int versionCode, Operator operator, MetadataBundle value) {
        this.mVersionCode = versionCode;
        this.acp = operator;
        this.acq = value;
        this.acr = e.b(value);
    }

    public <F> F a(f<F> fVar) {
        return fVar.b(this.acp, this.acr, getValue());
    }

    public int describeContents() {
        return 0;
    }

    public T getValue() {
        return this.acq.a(this.acr);
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
