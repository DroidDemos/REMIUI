package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class in implements SafeParcelable {
    public static final Creator<in> CREATOR;
    private double OB;
    private boolean OC;
    private int PD;
    private int PE;
    private ApplicationMetadata PP;
    private final int mVersionCode;

    static {
        CREATOR = new io();
    }

    public in() {
        this(3, Double.NaN, false, -1, null, -1);
    }

    in(int i, double d, boolean z, int i2, ApplicationMetadata applicationMetadata, int i3) {
        this.mVersionCode = i;
        this.OB = d;
        this.OC = z;
        this.PD = i2;
        this.PP = applicationMetadata;
        this.PE = i3;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof in)) {
            return false;
        }
        in inVar = (in) obj;
        return this.OB == inVar.OB && this.OC == inVar.OC && this.PD == inVar.PD && im.a(this.PP, inVar.PP) && this.PE == inVar.PE;
    }

    public ApplicationMetadata getApplicationMetadata() {
        return this.PP;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean hF() {
        return this.OC;
    }

    public int hashCode() {
        return kl.hashCode(Double.valueOf(this.OB), Boolean.valueOf(this.OC), Integer.valueOf(this.PD), this.PP, Integer.valueOf(this.PE));
    }

    public double hv() {
        return this.OB;
    }

    public int hw() {
        return this.PD;
    }

    public int hx() {
        return this.PE;
    }

    public void writeToParcel(Parcel out, int flags) {
        io.a(this, out, flags);
    }
}
