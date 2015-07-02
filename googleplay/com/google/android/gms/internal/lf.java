package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter;

public class lf implements SafeParcelable {
    public static final lg CREATOR;
    private final lh WR;
    private final int mVersionCode;

    static {
        CREATOR = new lg();
    }

    lf(int i, lh lhVar) {
        this.mVersionCode = i;
        this.WR = lhVar;
    }

    private lf(lh lhVar) {
        this.mVersionCode = 1;
        this.WR = lhVar;
    }

    public static lf a(FieldConverter<?, ?> fieldConverter) {
        if (fieldConverter instanceof lh) {
            return new lf((lh) fieldConverter);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public int describeContents() {
        lg lgVar = CREATOR;
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    lh iZ() {
        return this.WR;
    }

    public FieldConverter<?, ?> ja() {
        if (this.WR != null) {
            return this.WR;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    public void writeToParcel(Parcel out, int flags) {
        lg lgVar = CREATOR;
        lg.a(this, out, flags);
    }
}
