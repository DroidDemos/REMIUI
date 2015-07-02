package com.google.android.gms.internal;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class oa implements SafeParcelable, Cloneable {
    public static final ob CREATOR;
    private final String CB;
    private final boolean auf;
    private final int mVersionCode;

    static {
        CREATOR = new ob();
    }

    oa(int i, String str, boolean z) {
        kn.bk(str);
        this.mVersionCode = i;
        this.CB = str;
        this.auf = z;
    }

    public Object clone() {
        return new oa(this.mVersionCode, this.CB, this.auf);
    }

    public int describeContents() {
        ob obVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        oa oaVar = (oa) obj;
        return this.mVersionCode == oaVar.mVersionCode && TextUtils.equals(this.CB, oaVar.CB) && this.auf == oaVar.auf;
    }

    public String getId() {
        return this.CB;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), this.CB, Boolean.valueOf(this.auf));
    }

    public boolean isOptedIn() {
        return this.auf;
    }

    public String toString() {
        return "FeatureOptIn[id=" + this.CB + ", isOptedIn=" + this.auf + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        ob obVar = CREATOR;
        ob.a(this, dest, flags);
    }
}
