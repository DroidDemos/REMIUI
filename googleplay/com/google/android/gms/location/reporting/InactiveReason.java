package com.google.android.gms.location.reporting;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class InactiveReason implements SafeParcelable {
    public static final c CREATOR;
    private final int axD;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    public InactiveReason(int versionCode, int identifier, String name) {
        this.mVersionCode = versionCode;
        this.axD = identifier;
        this.mName = name;
    }

    public int describeContents() {
        c cVar = CREATOR;
        return 0;
    }

    public boolean equals(Object o) {
        return (o instanceof InactiveReason) && this.axD == ((InactiveReason) o).axD;
    }

    public int getIdentifier() {
        return this.axD;
    }

    public String getName() {
        return this.mName;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.axD;
    }

    public String toString() {
        return "InactiveReason{mVersionCode=" + this.mVersionCode + ", mIdentifier=" + this.axD + ", mName='" + this.mName + '\'' + '}';
    }

    public void writeToParcel(Parcel out, int flags) {
        c cVar = CREATOR;
        c.a(this, out, flags);
    }
}
