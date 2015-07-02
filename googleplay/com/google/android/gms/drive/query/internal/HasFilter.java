package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class HasFilter<T> extends AbstractFilter {
    public static final g CREATOR;
    final MetadataBundle acq;
    final MetadataField<T> acr;
    final int mVersionCode;

    static {
        CREATOR = new g();
    }

    HasFilter(int versionCode, MetadataBundle value) {
        this.mVersionCode = versionCode;
        this.acq = value;
        this.acr = e.b(value);
    }

    public <F> F a(f<F> fVar) {
        return fVar.d(this.acr, getValue());
    }

    public int describeContents() {
        return 0;
    }

    public T getValue() {
        return this.acq.a(this.acr);
    }

    public void writeToParcel(Parcel out, int flags) {
        g.a(this, out, flags);
    }
}
