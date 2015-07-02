package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class pr implements SafeParcelable {
    public static final ps CREATOR;
    public static final pr axv;
    public final String accountName;
    public final String awt;
    public final String axw;
    public final String localeString;
    public final int versionCode;

    static {
        axv = new pr("com.google.android.gms", Locale.ENGLISH, null);
        CREATOR = new ps();
    }

    public pr(int i, String str, String str2, String str3, String str4) {
        this.versionCode = i;
        this.axw = str;
        this.localeString = str2;
        this.accountName = str3;
        this.awt = str4;
    }

    public pr(String str, Locale locale, String str2) {
        this(1, str, locale.toString(), str2, null);
    }

    public int describeContents() {
        ps psVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof pr)) {
            return false;
        }
        pr prVar = (pr) object;
        return this.localeString.equals(prVar.localeString) && this.axw.equals(prVar.axw) && kl.equal(this.accountName, prVar.accountName) && kl.equal(this.awt, prVar.awt);
    }

    public int hashCode() {
        return kl.hashCode(this.axw, this.localeString, this.accountName);
    }

    public String toString() {
        return kl.j(this).a("clientPackageName", this.axw).a("locale", this.localeString).a("accountName", this.accountName).a("gCoreClientName", this.awt).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        ps psVar = CREATOR;
        ps.a(this, out, flags);
    }
}
