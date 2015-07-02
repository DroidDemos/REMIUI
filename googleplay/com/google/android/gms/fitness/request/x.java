package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class x implements SafeParcelable {
    public static final Creator<x> CREATOR;
    private final String agV;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new y();
    }

    x(int i, String str, String str2) {
        this.mVersionCode = i;
        this.mName = str;
        this.agV = str2;
    }

    private boolean a(x xVar) {
        return kl.equal(this.mName, xVar.mName) && kl.equal(this.agV, xVar.agV);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof x) && a((x) o));
    }

    public String getIdentifier() {
        return this.agV;
    }

    public String getName() {
        return this.mName;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.mName, this.agV);
    }

    public String toString() {
        return kl.j(this).a("name", this.mName).a("identifier", this.agV).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        y.a(this, dest, flags);
    }
}
