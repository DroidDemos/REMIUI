package com.google.android.gms.internal;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.people.data.Audience;

public class oc implements SafeParcelable, Cloneable {
    public static final od CREATOR;
    private final Audience aue;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new od();
    }

    oc(int i, String str, Audience audience) {
        kn.bk(str);
        this.mVersionCode = i;
        this.mName = str;
        this.aue = audience;
    }

    public Object clone() {
        return new oc(this.mVersionCode, this.mName, this.aue);
    }

    public int describeContents() {
        od odVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        oc ocVar = (oc) obj;
        return this.mVersionCode == ocVar.mVersionCode && TextUtils.equals(this.mName, ocVar.mName) && kl.equal(this.aue, ocVar.aue);
    }

    public Audience getAcl() {
        return this.aue;
    }

    public String getName() {
        return this.mName;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.mVersionCode), this.mName, this.aue);
    }

    public void writeToParcel(Parcel dest, int flags) {
        od odVar = CREATOR;
        od.a(this, dest, flags);
    }
}
