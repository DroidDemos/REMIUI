package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.copresence.Strategy;

public class on extends Strategy implements SafeParcelable {
    public static final Creator<on> CREATOR;
    private final boolean auY;
    private final boolean auZ;
    private final int ava;
    private final boolean avb;
    private final boolean avc;
    private final int mVersionCode;

    static {
        CREATOR = new oo();
    }

    on(int i, boolean z, boolean z2, int i2, boolean z3, boolean z4) {
        this.mVersionCode = i;
        this.auY = z;
        this.auZ = z2;
        this.ava = i2;
        this.avb = z3;
        this.avc = z4;
    }

    public on(boolean z, boolean z2, int i, boolean z3, boolean z4) {
        this(1, z, z2, i, z3, z4);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        on onVar = (on) object;
        return this.auY == onVar.auY && this.auZ == onVar.auZ && this.ava == onVar.ava && this.avb == onVar.avb && this.avc == onVar.avc;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Boolean.valueOf(this.auY), Boolean.valueOf(this.auZ));
    }

    public boolean isActive() {
        return this.auY;
    }

    public boolean pl() {
        return this.auZ;
    }

    public int pm() {
        return this.ava;
    }

    public boolean pn() {
        return this.avb;
    }

    public boolean po() {
        return this.avc;
    }

    public void writeToParcel(Parcel dest, int flags) {
        oo.a(this, dest, flags);
    }
}
