package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class j implements SafeParcelable {
    public static final Creator<j> CREATOR;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new k();
    }

    j(int i, String str) {
        this.mVersionCode = i;
        this.mName = str;
    }

    private boolean a(j jVar) {
        return kl.equal(this.mName, jVar.mName);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof j) && a((j) o));
    }

    public String getName() {
        return this.mName;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.mName);
    }

    public String toString() {
        return kl.j(this).a("name", this.mName).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
