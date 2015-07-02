package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;

public class InFilter<T> extends AbstractFilter {
    public static final h CREATOR;
    private final b<T> acB;
    final MetadataBundle acq;
    final int mVersionCode;

    static {
        CREATOR = new h();
    }

    InFilter(int versionCode, MetadataBundle value) {
        this.mVersionCode = versionCode;
        this.acq = value;
        this.acB = (b) e.b(value);
    }

    public <F> F a(f<F> fVar) {
        return fVar.b(this.acB, getValue());
    }

    public int describeContents() {
        return 0;
    }

    public T getValue() {
        return ((Collection) this.acq.a(this.acB)).iterator().next();
    }

    public void writeToParcel(Parcel out, int flags) {
        h.a(this, out, flags);
    }
}
