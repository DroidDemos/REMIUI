package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@Deprecated
public final class pn implements SafeParcelable {
    public static final po CREATOR;
    public final String axk;
    public final String axl;
    public final String axm;
    public final List<String> axn;
    public final String name;
    public final int versionCode;

    static {
        CREATOR = new po();
    }

    public pn(int i, String str, String str2, String str3, String str4, List<String> list) {
        this.versionCode = i;
        this.name = str;
        this.axk = str2;
        this.axl = str3;
        this.axm = str4;
        this.axn = list;
    }

    public int describeContents() {
        po poVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof pn)) {
            return false;
        }
        pn pnVar = (pn) object;
        return kl.equal(this.name, pnVar.name) && kl.equal(this.axk, pnVar.axk) && kl.equal(this.axl, pnVar.axl) && kl.equal(this.axm, pnVar.axm) && kl.equal(this.axn, pnVar.axn);
    }

    public int hashCode() {
        return kl.hashCode(this.name, this.axk, this.axl, this.axm);
    }

    public String toString() {
        return kl.j(this).a("name", this.name).a("address", this.axk).a("internationalPhoneNumber", this.axl).a("regularOpenHours", this.axm).a("attributions", this.axn).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        po poVar = CREATOR;
        po.a(this, parcel, flags);
    }
}
