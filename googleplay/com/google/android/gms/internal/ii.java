package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ii implements SafeParcelable {
    public static final Creator<ii> CREATOR;
    private String Pp;
    private final int mVersionCode;

    static {
        CREATOR = new ij();
    }

    public ii() {
        this(1, null);
    }

    ii(int i, String str) {
        this.mVersionCode = i;
        this.Pp = str;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ii)) {
            return false;
        }
        return im.a(this.Pp, ((ii) obj).Pp);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.Pp);
    }

    public String hp() {
        return this.Pp;
    }

    public void writeToParcel(Parcel out, int flags) {
        ij.a(this, out, flags);
    }
}
