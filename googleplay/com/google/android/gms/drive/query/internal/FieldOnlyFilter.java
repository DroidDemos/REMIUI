package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class FieldOnlyFilter extends AbstractFilter {
    public static final Creator<FieldOnlyFilter> CREATOR;
    final MetadataBundle acq;
    private final MetadataField<?> acr;
    final int mVersionCode;

    static {
        CREATOR = new b();
    }

    FieldOnlyFilter(int versionCode, MetadataBundle value) {
        this.mVersionCode = versionCode;
        this.acq = value;
        this.acr = e.b(value);
    }

    public <T> T a(f<T> fVar) {
        return fVar.d(this.acr);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
