package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;

public final class Scope implements SafeParcelable {
    public static final Creator<Scope> CREATOR;
    private final String Tg;
    final int mVersionCode;

    static {
        CREATOR = new e();
    }

    Scope(int versionCode, String scopeUri) {
        kn.b(scopeUri, (Object) "scopeUri must not be null or empty");
        this.mVersionCode = versionCode;
        this.Tg = scopeUri;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return !(o instanceof Scope) ? false : this.Tg.equals(((Scope) o).Tg);
    }

    public int hashCode() {
        return this.Tg.hashCode();
    }

    public String ik() {
        return this.Tg;
    }

    public String toString() {
        return this.Tg;
    }

    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
