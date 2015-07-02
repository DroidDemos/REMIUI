package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ox implements SafeParcelable {
    public static final oy CREATOR;
    private final int mVersionCode;
    public final String packageName;
    public final int uid;

    static {
        CREATOR = new oy();
    }

    ox(int i, int i2, String str) {
        this.mVersionCode = i;
        this.uid = i2;
        this.packageName = str;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ox)) {
            return false;
        }
        ox oxVar = (ox) o;
        return oxVar.uid == this.uid && kl.equal(oxVar.packageName, this.packageName);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.uid;
    }

    public String toString() {
        return String.format("%d:%s", new Object[]{Integer.valueOf(this.uid), this.packageName});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        oy.a(this, parcel, flags);
    }
}
