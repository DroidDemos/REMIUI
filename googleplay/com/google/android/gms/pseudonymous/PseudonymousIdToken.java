package com.google.android.gms.pseudonymous;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public class PseudonymousIdToken implements SafeParcelable {
    public static final Creator<PseudonymousIdToken> CREATOR;
    private String mValue;
    public final int mVersionCode;

    static {
        CREATOR = new a();
    }

    PseudonymousIdToken(int versionCode, String value) {
        this.mVersionCode = versionCode;
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof PseudonymousIdToken)) {
            return false;
        }
        PseudonymousIdToken pseudonymousIdToken = (PseudonymousIdToken) o;
        return kl.equal(this.mValue, pseudonymousIdToken.mValue) && this.mVersionCode == pseudonymousIdToken.mVersionCode;
    }

    public String getValue() {
        return this.mValue;
    }

    public int hashCode() {
        return kl.hashCode(this.mValue, Integer.valueOf(this.mVersionCode));
    }

    public String toString() {
        return "PseudonymousIdToken(" + this.mVersionCode + ")[" + this.mValue + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
